package com.matera.playlistedge.rest;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.niws.client.http.RestClient;

public class RetreivePlaylistCommand extends HystrixCommand<PlayListResponseMiddle> {

    private final RestClient restClient;
    private final JacksonJsonProvider jacksonJsonProvider;

    
    public RetreivePlaylistCommand(final RestClient restClient, final JacksonJsonProvider jacksonJsonProvider) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restClient = restClient;
        this.jacksonJsonProvider = jacksonJsonProvider;
    }

    @Override
    protected PlayListResponseMiddle run() throws Exception {
        ObjectMapper mapper = jacksonJsonProvider.locateMapper(Object.class, MediaType.APPLICATION_JSON_TYPE);

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