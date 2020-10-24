package com.wfuertes.playlistmiddle.service;

import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistmiddle.repository.PlaylistRepository;

import javax.inject.Inject;

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Inject
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist findById(String playlistId) {
        return playlistRepository.findById(playlistId)
                                 .orElseThrow(() -> new IllegalArgumentException("No Playlist found for id: " + playlistId));
    }
}
