package com.wfuertes.playlistmiddle.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.Song;
import com.wfuertes.playlistmiddle.config.TestModule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;


public class JooqPlaylistRepositoryTest {

    private static Injector injector;
    private PlaylistRepository repository;

    @BeforeClass
    public static void setupClass() {
        injector = Guice.createInjector(new TestModule());
    }

    @Before
    public void setup() {
        repository = injector.getInstance(PlaylistRepository.class);
    }

    @Test
    public void findById() {
        Optional<Playlist> byId = repository.findById("b72b3ec0-1643-11eb-aa16-0242ac110003");
        assertTrue(byId.isPresent());
    }

    @Test
    public void save() {
        Playlist playlist = new Playlist(UUID.randomUUID().toString(),
                                         "Test list",
                                         Collections.singletonList(new Song(
                                                 UUID.randomUUID().toString(),
                                                 "Test song",
                                                 "Test artist",
                                                 199,
                                                 OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime(),
                                                 OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime()
                                         )));

        repository.save(Collections.singleton(playlist));
        Optional<Playlist> byId = repository.findById(playlist.id());

        assertTrue(byId.isPresent());
    }
}