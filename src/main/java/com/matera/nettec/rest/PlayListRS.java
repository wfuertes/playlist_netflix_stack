/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.rest;

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

        return Response.ok("{\"id\" : \"12\", \"name\" : \"Labanda\"}").build();
    }
}