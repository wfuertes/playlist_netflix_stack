/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.config;

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
    }
}
