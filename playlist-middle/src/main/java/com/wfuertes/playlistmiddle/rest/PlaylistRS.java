package com.wfuertes.playlistmiddle.rest;

import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.wfuertes.playlistmiddle.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.singletonList;

@Path("playlist")
public class PlaylistRS {

    private final PlaylistService service;

    @Inject
    public PlaylistRS(PlaylistService service) {

        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist(@QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("limit") @DefaultValue("5") int limit) {

        checkArgument(page > 0, "Page must start in 1 or above");
        checkArgument(limit > 0, "Limit must be 1 or more");

        final PageQuery pageQuery = new PageQuery(page, limit);

        return Response.ok(new PlaylistResponseMiddle<>(200,
                                                        "Playlist Names retrieved with success",
                                                        singletonList(service.findAll(pageQuery))))
                       .build();
    }

    @GET
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist(@PathParam("playlistId") String playlistId) {

        return Response.ok(new PlaylistResponseMiddle<>(200,
                                                        "Playlists retrieved with success",
                                                        singletonList(service.findById(playlistId))))
                       .build();
    }
}
