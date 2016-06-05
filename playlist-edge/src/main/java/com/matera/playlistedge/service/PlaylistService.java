package com.matera.playlistedge.service;

import rx.Observable;
import rx.functions.Func1;

import com.google.inject.Inject;
import com.matera.playlist.rest.PlaylistRestClient;
import com.matera.playlistcore.entities.PlaylistResponseEdge;
import com.matera.playlistcore.entities.PlaylistResponseMiddle;

/**
 *
 *
 * @author wbatista
 */
public class PlaylistService {

    private final PlaylistRestClient playListClient;

    @Inject
    public PlaylistService(final PlaylistRestClient playListClient) {

        this.playListClient = playListClient;
    }

    public Observable<PlaylistResponseEdge> getPlayList() {

        return playListClient.getPlayList().map(toEdgePlaylist());
    }

    private Func1<PlaylistResponseMiddle, PlaylistResponseEdge> toEdgePlaylist() {

        return new Func1<PlaylistResponseMiddle, PlaylistResponseEdge>() {

            @Override
            public PlaylistResponseEdge call(PlaylistResponseMiddle middle) {

                PlaylistResponseEdge edge = new PlaylistResponseEdge();
                edge.setStatus(middle.getStatus());
                edge.setMessage(middle.getMessage());
                edge.getPlaylists().addAll(middle.getPlaylists());
                return edge;
            }
        };
    }
}
