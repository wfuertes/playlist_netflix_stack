package com.matera.playlistedge.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
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

    public static final String PLAYLIST_MAPPER = "PlayList";

    private static final String REST_CLIENT_NAME = "playlistmiddle";

    @Override
    protected void configureServlets() {

        final Map<String, String> initParams = new HashMap<>();
        initParams.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.matera");

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        // bind(JacksonJsonContextProvider.class).in(Scopes.SINGLETON);

        serve("/*").with(GuiceContainer.class, initParams);
    }

    @Provides
    @Singleton
    public RestClient getRestClient() {

        return (RestClient) ClientFactory.getNamedClient(REST_CLIENT_NAME);
    }

    @Provides
    @Singleton
    @Named(PLAYLIST_MAPPER)
    public ObjectMapper getObjectMapper() {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper;
    }

    @Provides
    @Singleton
    public JacksonJsonProvider getJacksonJsonProvider(@Named(PLAYLIST_MAPPER) final ObjectMapper mapper) {

        return new JacksonJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
    }
}
