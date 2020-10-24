package com.wfuertes.playlistmiddle.rest;

import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.wfuertes.playlistmiddle.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Collections.singletonList;

@Path("playlist")
public class PlaylistRS {

    private final PlaylistService service;

    @Inject
    public PlaylistRS(PlaylistService service) {

        this.service = service;
    }

    @GET
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist(@PathParam("playlistId") String playlistId) {

        return Response.ok(new PlaylistResponseMiddle(200,
                                                      "Playlists retrieved with success",
                                                      singletonList(service.findById(playlistId))))
                       .build();
    }
}
