/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.niws.client.http.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 *
 * @author wbatista
 */
@Path("playlist")
public class PlayListRS {

    private final RestClient restClient;

    @Inject
    public PlayListRS(final RestClient restClient) {

        this.restClient = restClient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retreivePlayList() {

        HttpRequest request =
            HttpRequest.newBuilder().uri("/playlistmiddle/playlist")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {

            ObjectMapper mapper = new ObjectMapper();
            String readValue = mapper.readValue(response.getInputStream(), String.class);
            return Response.ok(response.getPayload()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
