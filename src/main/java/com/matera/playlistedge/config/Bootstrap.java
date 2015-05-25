package com.matera.playlistedge.config;

import com.matera.playlist.config.PlayListClientModule;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.karyon.server.ServerBootstrap;

/**
 *
 * @author wbatista
 */
public class Bootstrap extends ServerBootstrap {

    @Override
    protected void configureBootstrapBinder(BootstrapBinder bootstrapBinder) {

        bootstrapBinder.install(new RestModule());
        bootstrapBinder.install(new PlayListClientModule());
    }
}
