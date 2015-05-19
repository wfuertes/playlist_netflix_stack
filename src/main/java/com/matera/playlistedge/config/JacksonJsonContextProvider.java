/*
 * Copyright 2014, Charter Communications, All rights reserved.
 */
package com.matera.playlistedge.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.inject.Provider;

import javax.inject.Singleton;

/**
 * ObjectMapper extension to support PascalCaseStrategy for property naming.
 *
 * @author wbatista
 */
@Singleton
public class JacksonJsonContextProvider implements Provider<JacksonJsonProvider> {

    /**
     * {@inheritDoc}
     */
    @Override
    public JacksonJsonProvider get() {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        
        return new JacksonJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
    }
}
