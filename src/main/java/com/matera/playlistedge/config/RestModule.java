/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.playlistedge.config;

import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.netflix.client.ClientFactory;
import com.netflix.niws.client.http.RestClient;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wbatista
 */
public class RestModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        final Map<String, String> initParams = new HashMap<>();
        initParams.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.matera");

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonContextProvider.class).in(Scopes.SINGLETON);

        serve("/*").with(GuiceContainer.class, initParams);
    }

    @Provides
    @Singleton
    public RestClient getRestClient() {

        return (RestClient) ClientFactory.getNamedClient("playlistmiddle");
    }
}
