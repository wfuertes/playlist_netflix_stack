package com.wfuertes.playlistedge.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wfuertes.playlistedge.service.PlaylistService;
import rx.functions.Func1;

import com.google.inject.Inject;
import com.wfuertes.playlistcore.entities.PlaylistResponseEdge;

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

        return service.getPlayList()
                      .map(toSuccessResponse())
                      .onErrorReturn(handleError())
                      .toBlocking()
                      .single();
    }

    private Func1<Throwable, Response> handleError() {

        return ex -> Response.serverError().build();
    }

    private Func1<PlaylistResponseEdge, Response> toSuccessResponse() {

        return playlist -> Response.status(playlist.status()).entity(playlist).build();
    }
}
