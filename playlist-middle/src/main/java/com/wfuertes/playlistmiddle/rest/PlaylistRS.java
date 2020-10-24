package com.wfuertes.playlistmiddle.rest;

import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.wfuertes.playlistmiddle.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlist")
public class PlaylistRS {

    private final PlaylistService service;

    @Inject
    public PlaylistRS(PlaylistService service) {

        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist() {

        return Response.ok(new PlaylistResponseMiddle(200,
                                                      "Playlists retrieved with success",
                                                      service.findAll())).build();
    }
}
