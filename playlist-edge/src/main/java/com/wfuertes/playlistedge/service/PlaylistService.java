package com.wfuertes.playlistedge.service;

import com.google.inject.Inject;
import com.wfuertes.playlist.rest.PlaylistRestClient;
import com.wfuertes.playlistcore.entities.Page;
import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.PlaylistResponseEdge;
import com.wfuertes.playlistcore.entities.PlaylistView;
import rx.Observable;

import java.util.List;

public class PlaylistService {

    private final PlaylistRestClient playListClient;

    @Inject
    public PlaylistService(final PlaylistRestClient playListClient) {
        this.playListClient = playListClient;
    }

    public Observable<PlaylistResponseEdge<List<Playlist>>> getPlayList(String playlistId) {
        return playListClient.getPlayList(playlistId)
                             .map(middle -> new PlaylistResponseEdge<>(middle.status(), middle.message(), middle.content()));
    }

    public Observable<PlaylistResponseEdge<Page<PlaylistView>>> getPlayListPage(PageQuery query) {
        return playListClient.getPlayListPage(query)
                             .map(middle -> new PlaylistResponseEdge<>(middle.status(), middle.message(), middle.content()));
    }

}
