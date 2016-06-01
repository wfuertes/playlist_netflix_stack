/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.playlistmiddle.rest;

import com.matera.playlistcore.entities.PlayList;
import com.matera.playlistcore.entities.PlayListResponseMiddle;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 *
 * @author wbatista
 */
@Path("playlist")
public class PlayListRS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retreivePlayList() {

        PlayList p = new PlayList();
        p.setId(System.currentTimeMillis() + 1);
        p.setName("Classic Rock");
        p.getMusics().add("Highway to Hell");
        p.getMusics().add("Jump");
        p.getMusics().add("Sultans Of Swing");

        PlayListResponseMiddle responseMiddle = new PlayListResponseMiddle();
        responseMiddle.setPlayLists(Arrays.asList(p));

        return Response.ok(responseMiddle).build();
    }
}
