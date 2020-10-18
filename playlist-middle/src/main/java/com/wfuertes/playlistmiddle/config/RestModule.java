package com.wfuertes.playlistmiddle.config;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Map;

public class RestModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        final Map<String, String> initParams = ImmutableMap.<String, String>builder()
                .put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.wfuertes")
                .build();

        serve("/*").with(GuiceContainer.class, initParams);
    }
}
