package com.matera.playlistcore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class PlaylistJacksonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).annotatedWith(Playlist.class).toProvider(PlaylistObjectMapperProvider.class)
                .asEagerSingleton();
    }

    @Provides
    @Singleton
    public JacksonJsonProvider getJacksonJsonProvider(@Playlist final ObjectMapper mapper) {
        return new JacksonJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
    }
}
