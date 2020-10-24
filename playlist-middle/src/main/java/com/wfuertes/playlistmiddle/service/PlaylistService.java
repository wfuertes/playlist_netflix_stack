package com.wfuertes.playlistmiddle.service;

import com.wfuertes.playlistcore.entities.Playlist;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class PlaylistService {

    public List<Playlist> findAll() {
        return fakePlaylist();
    }

    private static List<Playlist> fakePlaylist() {
        return IntStream.range(0, 10)
                        .mapToObj(i -> new Playlist(UUID.randomUUID().toString(),
                                                    "Classic Rock " + i,
                                                    asList("Highway to Hell",
                                                           "Jump",
                                                           "Sultans Of Swing")))
                        .collect(Collectors.toList());
    }
}
