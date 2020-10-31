package com.wfuertes.playlistmiddle.repository;

import com.wfuertes.playlistcore.entities.Page;
import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.PlaylistView;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface PlaylistRepository {

    Optional<Playlist> findById(String playlistId);

    void save(Collection<Playlist> playlists);

    Page<PlaylistView> findAll(PageQuery query);
}
