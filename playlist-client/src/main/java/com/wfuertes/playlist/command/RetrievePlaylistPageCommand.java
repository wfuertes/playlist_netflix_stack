package com.wfuertes.playlist.command;

import com.google.common.reflect.TypeToken;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.niws.client.http.RestClient;
import com.wfuertes.playlistcore.entities.Page;
import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.wfuertes.playlistcore.entities.PlaylistView;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static java.util.Collections.emptyList;

public class RetrievePlaylistPageCommand extends HystrixCommand<PlaylistResponseMiddle<Page<PlaylistView>>> {

    private static final String GROUP = "PlaylistMiddle";
    private static final String PLAYLIST_URL = "/playlistmiddle/playlist/";

    private static final Setter COMMAND_SETTER = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                                                       .andCommandKey(HystrixCommandKey.Factory.asKey(
                                                               RetrievePlaylistPageCommand.class.getSimpleName()));

    private final RestClient restClient;
    private final PageQuery query;

    public RetrievePlaylistPageCommand(final RestClient restClient, PageQuery query) {
        super(COMMAND_SETTER);
        this.restClient = restClient;
        this.query = query;
    }

    @Override
    protected PlaylistResponseMiddle<Page<PlaylistView>> run() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(PLAYLIST_URL)
                                         .queryParams("page", String.valueOf(query.number()))
                                         .queryParams("limit", String.valueOf(query.limit()))
                                         .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

        try (HttpResponse response = restClient.executeWithLoadBalancer(request)) {
            return response.getEntity(new TypeToken<PlaylistResponseMiddle<Page<PlaylistView>>>() {
            });
        }
    }

    @Override
    protected PlaylistResponseMiddle<Page<PlaylistView>> getFallback() {

        Page<PlaylistView> fallbackPage = new Page<>(0, 0, emptyList());

        if (isFailedExecution()) {
            return new PlaylistResponseMiddle<>(500, getFailedExecutionException().getMessage(), fallbackPage);
        }
        return new PlaylistResponseMiddle<>(500,
                                            String.format("It wasn't possible retrieve playlist from: %s?page=%s&limit=%s",
                                                          PLAYLIST_URL,
                                                          query.number(),
                                                          query.limit()),
                                            fallbackPage);
    }
}