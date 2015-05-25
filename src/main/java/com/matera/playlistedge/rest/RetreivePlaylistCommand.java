package com.matera.playlistedge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.niws.client.http.RestClient;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class RetreivePlaylistCommand extends HystrixCommand<PlayListResponseMiddle> {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    public RetreivePlaylistCommand(final RestClient restClient, final ObjectMapper mapper) {

        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restClient = restClient;
        this.mapper = mapper;
    }

    @Override
    protected PlayListResponseMiddle run() throws Exception {

        HttpRequest request =
            HttpRequest.newBuilder().uri("/playlistmiddle/playlist")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {

            PlayListResponseMiddle playListResponseMiddle =
                mapper.readValue(response.getInputStream(), PlayListResponseMiddle.class);
            return playListResponseMiddle;
        }
    }
}
