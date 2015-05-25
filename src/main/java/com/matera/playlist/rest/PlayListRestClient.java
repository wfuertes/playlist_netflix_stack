package com.matera.playlist.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.matera.playlist.command.RetrievePlayListCommand;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.netflix.niws.client.http.RestClient;

import rx.Observable;

/**
 *
 *
 * @author wbatista
 */
public class PlayListRestClient {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    @Inject
    private PlayListRestClient(final RestClient restClient, @Named("PlayList") final ObjectMapper mapper) {

        this.restClient = restClient;
        this.mapper = mapper;
    }

    public Observable<PlayListResponseMiddle> getPlayList() {

        RetrievePlayListCommand command = new RetrievePlayListCommand(restClient, mapper);
        return command.toObservable();
    }
}
