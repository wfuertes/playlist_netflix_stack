package com.matera.playlist.config;

import com.google.inject.AbstractModule;
import com.matera.playlist.rest.PlayListRestClient;

/**
 *
 *
 * @author wbatista
 */
public class PlayListClientModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(PlayListRestClient.class);
    }
}
