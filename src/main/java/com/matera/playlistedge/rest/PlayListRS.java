package com.matera.playlistedge.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rx.functions.Func1;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Inject;
import com.matera.playlistcore.entities.PlayListResponseEdge;
import com.matera.playlistcore.entities.PlayListResponseMiddle;
import com.netflix.niws.client.http.RestClient;

/**
 *
 *
 * @author wbatista
 */
@Path("playlist")
public class PlayListRS {

    private final RestClient restClient;
    private final JacksonJsonProvider jacksonJsonProvider;

    @Inject
    public PlayListRS(final RestClient restClient, final JacksonJsonProvider jacksonJsonProvider) {

        this.restClient = restClient;
        this.jacksonJsonProvider = jacksonJsonProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retreivePlayList() {
    	RetreivePlaylistCommand command = new RetreivePlaylistCommand(restClient, jacksonJsonProvider);
    	return command.toObservable()
    			.map(toEdgePlayList())
    			.map(toSuccessResponse())
    			.onErrorReturn(handleError())
    			.toBlocking().first();
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

    private Func1<PlayListResponseMiddle, PlayListResponseEdge> toEdgePlayList() {

        return new Func1<PlayListResponseMiddle, PlayListResponseEdge>() {

            @Override
            public PlayListResponseEdge call(PlayListResponseMiddle middle) {

                PlayListResponseEdge edge = new PlayListResponseEdge();
                edge.setPlayLists(middle.getPlayLists());
                return edge;
            }
        };
    }
}
