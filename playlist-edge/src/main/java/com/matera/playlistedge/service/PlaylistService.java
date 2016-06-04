package com.matera.playlistedge.service;

import com.google.inject.Inject;
import com.matera.playlist.rest.PlaylistRestClient;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistcore.entities.PlaylistResponseMiddle;

import rx.Observable;
import rx.functions.Func1;

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

    public Observable<PlayListResponseEdge> getPlayList() {

        return playListClient.getPlayList().map(toEdgePlayList());
    }

    private Func1<PlaylistResponseMiddle, PlayListResponseEdge> toEdgePlayList() {

        return new Func1<PlaylistResponseMiddle, PlayListResponseEdge>() {

            @Override
            public PlayListResponseEdge call(PlaylistResponseMiddle middle) {

                PlayListResponseEdge edge = new PlayListResponseEdge();
                edge.setPlayLists(middle.getPlaylists());
                return edge;
            }
        };
    }
}
