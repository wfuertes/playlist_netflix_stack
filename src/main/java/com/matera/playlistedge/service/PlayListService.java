package com.matera.playlistedge.service;

import com.google.inject.Inject;
import com.matera.playlist.rest.PlayListRestClient;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistcore.entities.PlayListResponseMiddle;

import rx.Observable;
import rx.functions.Func1;

/**
 *
 *
 * @author wbatista
 */
public class PlayListService {

    private final PlayListRestClient playListClient;

    @Inject
    public PlayListService(final PlayListRestClient playListClient) {

        this.playListClient = playListClient;
    }

    public Observable<PlayListResponseEdge> getPlayList() {

        return playListClient.getPlayList().map(toEdgePlayList());
    }

    private Func1<PlayListResponseMiddle, PlayListResponseEdge> toEdgePlayList() {

        return new Func1<PlayListResponseMiddle, PlayListResponseEdge>() {

            @Override
            public PlayListResponseEdge call(PlayListResponseMiddle middle) {

                PlayListResponseEdge edge = new PlayListResponseEdge();
                edge.setPlayLists(middle.getPlayLists());
                return edge;
            }
        };
    }
}
