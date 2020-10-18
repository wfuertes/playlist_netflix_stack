package com.wfuertes.playlist.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.wfuertes.playlist.rest.PlaylistRestClient;
import com.netflix.client.ClientFactory;
import com.netflix.niws.client.http.RestClient;

public class PlaylistClientModule extends AbstractModule {

    public static final String PLAYLIST_MIDDLE_RIBBON = "playlistmiddle";

    @Override
    protected void configure() {

        bind(PlaylistRestClient.class);
    }

    @Provides
    @Singleton
    public RestClient getRestClient() {

        return (RestClient) ClientFactory.getNamedClient(PLAYLIST_MIDDLE_RIBBON);
    }
}
