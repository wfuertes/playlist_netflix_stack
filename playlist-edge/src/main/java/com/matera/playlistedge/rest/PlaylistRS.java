package com.matera.playlistedge.rest;

import com.google.inject.Inject;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistedge.service.PlaylistService;

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
public class PlaylistRS {

    private final PlaylistService service;

    @Inject
    public PlaylistRS(final PlaylistService service) {

        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist() {

        return service.getPlayList().map(toSuccessResponse()).onErrorReturn(handleError()).toBlocking().single();
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
}
