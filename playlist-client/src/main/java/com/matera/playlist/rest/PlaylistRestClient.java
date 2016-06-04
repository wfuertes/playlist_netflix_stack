package com.matera.playlist.rest;

import rx.Observable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.matera.playlist.command.RetrievePlaylistCommand;
import com.matera.playlistcore.config.Playlist;
import com.matera.playlistcore.entities.PlaylistResponseMiddle;
import com.netflix.niws.client.http.RestClient;

/**
 *
 *
 * @author wbatista
 */
public class PlaylistRestClient {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    @Inject
    private PlaylistRestClient(final RestClient restClient, @Playlist final ObjectMapper mapper) {

        this.restClient = restClient;
        this.mapper = mapper;
    }

    public Observable<PlaylistResponseMiddle> getPlayList() {

        RetrievePlaylistCommand command = new RetrievePlaylistCommand(restClient, mapper);
        return command.toObservable();
    }
}
