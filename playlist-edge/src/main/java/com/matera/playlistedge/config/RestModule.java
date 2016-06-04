package com.matera.playlistedge.config;

import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 *
 * @author wbatista
 */
public class RestModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        final Map<String, String> initParams = new HashMap<>();
        initParams.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.matera");

        serve("/*").with(GuiceContainer.class, initParams);
    }
}
