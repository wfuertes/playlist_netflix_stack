package com.matera.playlistedge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Inject;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.niws.client.http.RestClient;

import java.util.function.Function;

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
    private final JacksonJsonProvider jacksonJsonProvider;

    @Inject
    public PlayListRS(final RestClient restClient, final JacksonJsonProvider jacksonJsonProvider) {

        this.restClient = restClient;
        this.jacksonJsonProvider = jacksonJsonProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retreivePlayList() {

        ObjectMapper mapper = jacksonJsonProvider.locateMapper(Object.class, MediaType.APPLICATION_JSON_TYPE);

        HttpRequest request =
            HttpRequest.newBuilder().uri("/playlistmiddle/playlist")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {

            PlayListResponseMiddle playListResponseMiddle =
                mapper.readValue(response.getInputStream(), PlayListResponseMiddle.class);

            PlayListResponseEdge responseEdge = getPlayListFunction().apply(playListResponseMiddle);

            return Response.ok(responseEdge).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    private Function<PlayListResponseMiddle, PlayListResponseEdge> getPlayListFunction() {

        return new Function<PlayListResponseMiddle, PlayListResponseEdge>() {

            @Override
            public PlayListResponseEdge apply(PlayListResponseMiddle middle) {

                PlayListResponseEdge edge = new PlayListResponseEdge();
                edge.setPlayLists(middle.getPlayLists());
                return edge;
            }
        };
    }
}
