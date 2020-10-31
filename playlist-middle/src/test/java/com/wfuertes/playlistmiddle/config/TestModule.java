package com.wfuertes.playlistmiddle.config;

import com.google.inject.AbstractModule;
import com.wfuertes.playlistcore.config.PlaylistJacksonModule;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        binder().install(new PlaylistJacksonModule());
        binder().install(new DatabaseModule(() -> {
            final HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.h2.Driver");
            config.setJdbcUrl("jdbc:h2:mem:playlist_middle;MODE=MYSQL;DATABASE_TO_LOWER=TRUE");
            return new HikariDataSource(config);
        }));
    }
}