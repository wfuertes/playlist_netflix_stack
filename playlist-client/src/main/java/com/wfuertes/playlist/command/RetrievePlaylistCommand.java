package com.wfuertes.playlist.command;

import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.niws.client.http.RestClient;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static java.util.Collections.emptyList;

public class RetrievePlaylistCommand extends HystrixCommand<PlaylistResponseMiddle> {

    private static final String GROUP = "PlaylistMiddle";
    private static final String PLAYLIST_URL = "/playlistmiddle/playlist/";

    private static final Setter COMMAND_SETTER = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                                                       .andCommandKey(HystrixCommandKey.Factory.asKey(
                                                               RetrievePlaylistCommand.class.getSimpleName()));

    private final RestClient restClient;
    private final String playlistId;

    public RetrievePlaylistCommand(final RestClient restClient, final String playlistId) {
        super(COMMAND_SETTER);
        this.restClient = restClient;
        this.playlistId = playlistId;
    }

    @Override
    protected PlaylistResponseMiddle run() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(PLAYLIST_URL + playlistId)
                                         .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {

            return response.getEntity(PlaylistResponseMiddle.class);
        }
    }

    @Override
    protected PlaylistResponseMiddle getFallback() {
        if (isFailedExecution()) {
            return new PlaylistResponseMiddle(500, getFailedExecutionException().getMessage(), emptyList());
        }
        return new PlaylistResponseMiddle(500, "It wasn't possible retrieve playlist from: " + PLAYLIST_URL, emptyList());
    }
}