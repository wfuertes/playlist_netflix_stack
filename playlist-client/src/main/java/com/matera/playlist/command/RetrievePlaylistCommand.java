/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.playlist.command;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matera.playlistcore.entities.PlaylistResponseMiddle;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.niws.client.http.RestClient;

/**
 *
 *
 * @author user
 */
public class RetrievePlaylistCommand extends HystrixCommand<PlaylistResponseMiddle> {

    private static final String PLAYLIST_URL = "/playlistmiddle/playlist";

    private final RestClient restClient;
    private final ObjectMapper mapper;

    public RetrievePlaylistCommand(final RestClient restClient, final ObjectMapper mapper) {

        super(HystrixCommandGroupKey.Factory.asKey("PlaylistMiddle"));
        this.restClient = restClient;
        this.mapper = mapper;
    }

    @Override
    protected PlaylistResponseMiddle run() throws Exception {

        HttpRequest request =
            HttpRequest.newBuilder().uri(PLAYLIST_URL).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {

            PlaylistResponseMiddle playListResponseMiddle =
                mapper.readValue(response.getInputStream(), PlaylistResponseMiddle.class);
            return playListResponseMiddle;
        }
    }
}