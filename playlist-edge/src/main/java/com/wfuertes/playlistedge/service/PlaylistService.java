package com.wfuertes.playlistedge.service;

import rx.Observable;
import rx.functions.Func1;

import com.google.inject.Inject;
import com.wfuertes.playlist.rest.PlaylistRestClient;
import com.wfuertes.playlistcore.entities.PlaylistResponseEdge;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;

public class PlaylistService {

    private final PlaylistRestClient playListClient;

    @Inject
    public PlaylistService(final PlaylistRestClient playListClient) {

        this.playListClient = playListClient;
    }

    public Observable<PlaylistResponseEdge> getPlayList(String playlistId) {

        return playListClient.getPlayList(playlistId).map(toEdgePlaylist());
    }

    private Func1<PlaylistResponseMiddle, PlaylistResponseEdge> toEdgePlaylist() {

        return middle -> new PlaylistResponseEdge(middle.status(),
                                                  middle.message(),
                                                  middle.playlists());
    }
}
