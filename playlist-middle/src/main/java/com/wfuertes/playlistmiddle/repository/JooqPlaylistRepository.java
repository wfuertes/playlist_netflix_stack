package com.wfuertes.playlistmiddle.repository;

import com.wfuertes.playlistcore.entities.Playlist;
import org.jooq.DSLContext;
import org.jooq.Record1;

import javax.inject.Inject;
import java.util.Optional;

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
                                                  context.select(SONGS.NAME)
                                                         .from(PLAYLIST_SONGS)
                                                         .innerJoin(SONGS)
                                                         .on(SONGS.ID.eq(PLAYLIST_SONGS.SONG_ID))
                                                         .where(PLAYLIST_SONGS.PLAYLIST_ID.eq(playlistId))
                                                         .fetch(Record1::value1)));
    }
}
