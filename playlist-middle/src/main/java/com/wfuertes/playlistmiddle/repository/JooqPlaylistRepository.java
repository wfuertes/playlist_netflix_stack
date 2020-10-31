package com.wfuertes.playlistmiddle.repository;

import com.wfuertes.playlistcore.entities.Page;
import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.PlaylistView;
import com.wfuertes.playlistcore.entities.Song;
import com.wfuertes.playlistmiddle.sql.tables.records.PlaylistSongsRecord;
import com.wfuertes.playlistmiddle.sql.tables.records.PlaylistsRecord;
import com.wfuertes.playlistmiddle.sql.tables.records.SongsRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wfuertes.playlistmiddle.sql.Tables.PLAYLISTS;
import static com.wfuertes.playlistmiddle.sql.Tables.PLAYLIST_SONGS;
import static com.wfuertes.playlistmiddle.sql.Tables.SONGS;

public class JooqPlaylistRepository implements PlaylistRepository {

    private final DSLContext context;

    @Inject
    public JooqPlaylistRepository(DSLContext context) {
        this.context = context;
    }

    @Override
    public Optional<Playlist> findById(String playlistId) {

        return context.selectFrom(PLAYLISTS)
                      .where(PLAYLISTS.ID.eq(playlistId))
                      .fetchOptional()
                      .map(record -> new Playlist(record.getId(),
                                                  record.getName(),
                                                  context.select(SONGS.asterisk())
                                                         .from(PLAYLIST_SONGS)
                                                         .innerJoin(SONGS)
                                                         .on(SONGS.ID.eq(PLAYLIST_SONGS.SONG_ID))
                                                         .where(PLAYLIST_SONGS.PLAYLIST_ID.eq(playlistId))
                                                         .fetchInto(SongsRecord.class)
                                                         .stream()
                                                         .map(song -> new Song(
                                                                 song.getId(),
                                                                 song.getName(),
                                                                 song.getArtist(),
                                                                 song.getDuration(),
                                                                 song.getCreatedAt(),
                                                                 song.getUpdatedAt()))
                                                         .collect(Collectors.toList())));
    }

    @Override
    public void save(Collection<Playlist> playlists) {
        context.transaction(config -> {
            final List<PlaylistsRecord> playlistsRecords = playlists.stream()
                                                                    .map(playlist -> new PlaylistsRecord(
                                                                            playlist.id(),
                                                                            playlist.name(),
                                                                            OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime(),
                                                                            OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()
                                                                    )).collect(Collectors.toList());

            final List<SongsRecord> songsRecords = playlists.stream()
                                                            .map(Playlist::songs)
                                                            .flatMap(Collection::stream)
                                                            .map(song -> new SongsRecord(song.id(),
                                                                                         song.name(),
                                                                                         song.artist(),
                                                                                         song.duration(),
                                                                                         song.createdAt(),
                                                                                         song.updatedAt()))
                                                            .collect(Collectors.toList());

            final List<PlaylistSongsRecord> playlistSongsRecords = playlists.stream()
                                                                            .flatMap(playlist -> playlist.songs()
                                                                                                         .stream()
                                                                                                         .map(song -> new PlaylistSongsRecord(
                                                                                                                 playlist.id(),
                                                                                                                 song.id())))
                                                                            .collect(Collectors.toList());

            DSL.using(config)
               .batchInsert(Stream.of(playlistsRecords, songsRecords, playlistSongsRecords)
                                  .flatMap(Collection::stream)
                                  .collect(Collectors.toList()))
               .execute();
        });
    }

    @Override
    public Page<PlaylistView> findAll(PageQuery query) {
        final List<PlaylistView> playlists = context.selectFrom(PLAYLISTS)
                                                    .limit(query.limit())
                                                    .offset(query.offset())
                                                    .fetch(record -> new PlaylistView(record.getId(), record.getName()));

        return new Page<>(query.number(), playlists.size(), playlists);
    }
}
