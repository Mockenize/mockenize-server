package com.mockenize;

import com.mockenize.controller.*;
import com.mockenize.exception.ExceptionMapper;
import com.mockenize.filter.CrossOriginFilter;
import com.mockenize.filter.RequestLoggingFilter;
import com.mockenize.filter.ResponseLoggingFilter;
import com.mockenize.provider.JacksonProvider;
import com.mockenize.provider.TextPlainMessageBodyWriter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerProviders();
        registerFilters();
        registerControllers();
    }

    private void registerProviders() {
        register(ExceptionMapper.class);
        register(TextPlainMessageBodyWriter.class);
        register(JacksonProvider.class);
    }

    private void registerControllers() {
        register(ScriptsController.class);
        register(MockenizeController.class);
        register(MocksController.class);
        register(ProxiesController.class);
        register(LogController.class);
    }

    private void registerFilters() {
        register(CrossOriginFilter.class);
        register(RequestLoggingFilter.class);
        register(ResponseLoggingFilter.class);
    }
}
