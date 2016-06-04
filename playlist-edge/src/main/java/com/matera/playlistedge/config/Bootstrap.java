package com.matera.playlistedge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.matera.playlist.config.PlaylistClientModule;
import com.matera.playlistcore.config.Playlist;
import com.matera.playlistcore.config.PlaylistJacksonModule;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.karyon.server.ServerBootstrap;

/**
 *
 * @author wbatista
 */
public class Bootstrap extends ServerBootstrap {

    @Override
    protected void configureBootstrapBinder(BootstrapBinder binder) {

        binder.install(new RestModule());
        binder.install(new PlaylistClientModule());
        binder.install(new PlaylistJacksonModule());
    }

    @Provides
    @Singleton
    public ObjectMapper mapper(@Playlist ObjectMapper objectMapper) {
        return objectMapper;
    }
}
