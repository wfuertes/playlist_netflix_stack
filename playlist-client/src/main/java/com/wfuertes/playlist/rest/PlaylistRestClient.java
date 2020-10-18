package com.wfuertes.playlist.rest;

import rx.Observable;

import com.google.inject.Inject;
import com.wfuertes.playlist.command.RetrievePlaylistCommand;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.netflix.niws.client.http.RestClient;

public class PlaylistRestClient {

    private final RestClient restClient;

    @Inject
    private PlaylistRestClient(final RestClient restClient) {

        this.restClient = restClient;
    }

    public Observable<PlaylistResponseMiddle> getPlayList() {

        RetrievePlaylistCommand command = new RetrievePlaylistCommand(restClient);
        return command.toObservable();
    }
}
