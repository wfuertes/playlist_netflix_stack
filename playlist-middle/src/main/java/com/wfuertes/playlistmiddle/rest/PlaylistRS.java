package com.wfuertes.playlistmiddle.rest;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;

@Path("playlist")
public class PlaylistRS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePlaylist() {

        Playlist p = new Playlist();
        p.setId(System.currentTimeMillis() + 1);
        p.setName("Classic Rock I");
        p.getMusics().add("Highway to Hell");
        p.getMusics().add("Jump");
        p.getMusics().add("Sultans Of Swing");

        Playlist p2 = new Playlist();
        p2.setId(System.currentTimeMillis() + 1);
        p2.setName("Classic Rock II");
        p2.getMusics().add("Highway to Hell");
        p2.getMusics().add("Jump");
        p2.getMusics().add("Sultans Of Swing");

        Playlist p3 = new Playlist();
        p3.setId(System.currentTimeMillis() + 1);
        p3.setName("Classic Rock III");
        p3.getMusics().add("Highway to Hell");
        p3.getMusics().add("Jump");
        p3.getMusics().add("Sultans Of Swing");

        PlaylistResponseMiddle responseMiddle = new PlaylistResponseMiddle();
        responseMiddle.setStatus(200);
        responseMiddle.setMessage("Playlists retrieved with success");
        responseMiddle.getPlaylists().addAll(Arrays.asList(p, p2, p3));

        return Response.ok(responseMiddle).build();
    }
}
