package org.mockenize;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mockenize.controller.*;
import org.mockenize.provider.filter.CrossOriginFilter;
import org.mockenize.provider.filter.RequestLoggingFilter;
import org.mockenize.provider.filter.ResponseLoggingFilter;
import org.mockenize.provider.mapper.ExceptionMapper;
import org.mockenize.provider.mapper.JacksonProvider;
import org.mockenize.provider.mapper.TextPlainMessageBodyWriter;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerProviders();
        registerFilters();
        registerControllers();

        register(MultiPartFeature.class);
        
        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/_client.*");
    }

    private void registerProviders() {
        register(ExceptionMapper.class);
        register(TextPlainMessageBodyWriter.class);
        register(JacksonProvider.class);
    }

    private void registerControllers() {
        register(MockenizeController.class);
        register(ScriptsController.class);
        register(MocksController.class);
        register(ProxiesController.class);
        register(LogController.class);
        register(FileController.class);
    }

    private void registerFilters() {
        register(CrossOriginFilter.class);
        register(RequestLoggingFilter.class);
        register(ResponseLoggingFilter.class);
    }
}
