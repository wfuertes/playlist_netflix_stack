package com.wfuertes.playlistedge.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wfuertes.playlistcore.entities.PageQuery;
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
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@PathParam("playlistId") String playlistId) {

        return service.getPlayList(playlistId)
                      .map(toSuccessResponse())
                      .onErrorReturn(handleError())
                      .toBlocking()
                      .single();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist(@QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("limit") @DefaultValue("5") int limit) {

        return service.getPlayListPage(new PageQuery(page, limit))
                      .map(toSuccessResponse())
                      .onErrorReturn(handleError())
                      .toBlocking()
                      .single();
    }

    private Func1<Throwable, Response> handleError() {
        return ex -> Response.serverError().build();
    }

    private Func1<PlaylistResponseEdge<?>, Response> toSuccessResponse() {
        return playlist -> Response.status(playlist.status()).entity(playlist).build();
    }
}
