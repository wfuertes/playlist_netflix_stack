package com.wfuertes.playlist.rest;

import com.wfuertes.playlist.command.RetrievePlaylistPageCommand;
import com.wfuertes.playlistcore.entities.Page;
import com.wfuertes.playlistcore.entities.PageQuery;
import com.wfuertes.playlistcore.entities.Playlist;
import com.wfuertes.playlistcore.entities.PlaylistView;
import rx.Observable;

import com.google.inject.Inject;
import com.wfuertes.playlist.command.RetrievePlaylistCommand;
import com.wfuertes.playlistcore.entities.PlaylistResponseMiddle;
import com.netflix.niws.client.http.RestClient;

import java.util.List;

public class PlaylistRestClient {

    private final RestClient restClient;

    @Inject
    private PlaylistRestClient(final RestClient restClient) {
        this.restClient = restClient;
    }

    public Observable<PlaylistResponseMiddle<List<Playlist>>> getPlayList(String playlistId) {
        return new RetrievePlaylistCommand(restClient, playlistId).toObservable();
    }

    public Observable<PlaylistResponseMiddle<Page<PlaylistView>>> getPlayListPage(PageQuery query) {
        return new RetrievePlaylistPageCommand(restClient, query).toObservable();
    }
}
