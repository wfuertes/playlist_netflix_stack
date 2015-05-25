package com.matera.playlistedge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.matera.playlistedge.config.RestModule;
import com.netflix.niws.client.http.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rx.functions.Func1;

/**
 *
 *
 * @author wbatista
 */
@Path("playlist")
public class PlayListRS {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    @Inject
    public PlayListRS(final RestClient restClient, @Named(RestModule.PLAYLIST_MAPPER) final ObjectMapper mapper) {

        this.restClient = restClient;
        this.mapper = mapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayList() {

        RetreivePlaylistCommand command = new RetreivePlaylistCommand(restClient, mapper);
        return command.toObservable().map(toEdgePlayList()).map(toSuccessResponse()).onErrorReturn(handleError())
            .toBlocking().single();
    }

    private Func1<Throwable, Response> handleError() {

        return new Func1<Throwable, Response>() {

            @Override
            public Response call(Throwable ex) {

                ex.printStackTrace();
                return Response.serverError().build();
            }
        };
    }

    private Func1<PlayListResponseEdge, Response> toSuccessResponse() {

        return new Func1<PlayListResponseEdge, Response>() {

            @Override
            public Response call(PlayListResponseEdge playlist) {

                return Response.ok(playlist).build();
            }
        };
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
