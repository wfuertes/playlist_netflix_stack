/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.playlistmiddle.config;

import com.google.inject.Scopes;
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
}
