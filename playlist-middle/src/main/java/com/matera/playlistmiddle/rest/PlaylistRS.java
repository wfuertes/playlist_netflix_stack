package com.matera.playlistmiddle.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.matera.playlistcore.entities.Playlist;
import com.matera.playlistcore.entities.PlaylistResponseMiddle;

/**
 * @author wbatista
 */
@Path("playlist")
public class PlaylistRS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retreivePlaylist() {

        Playlist p = new Playlist();
        p.setId(System.currentTimeMillis() + 1);
        p.setName("Classic Rock");
        p.getMusics().add("Highway to Hell");
        p.getMusics().add("Jump");
        p.getMusics().add("Sultans Of Swing");

        PlaylistResponseMiddle responseMiddle = new PlaylistResponseMiddle();
        responseMiddle.getPlaylists().add(p);

        return Response.ok(responseMiddle).build();
    }
}
