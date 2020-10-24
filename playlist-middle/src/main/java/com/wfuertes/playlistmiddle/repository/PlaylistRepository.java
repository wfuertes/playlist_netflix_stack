package com.wfuertes.playlistmiddle.repository;

import com.wfuertes.playlistcore.entities.Playlist;

import java.util.Optional;

public interface PlaylistRepository {

    Optional<Playlist> findById(String playlistId);
}
