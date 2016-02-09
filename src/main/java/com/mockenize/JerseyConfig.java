package com.mockenize;

import com.mockenize.controller.*;
import com.mockenize.exception.ExceptionMapper;
import com.mockenize.filter.CrossOriginFilter;
import com.mockenize.filter.RequestLoggingFilter;
import com.mockenize.filter.ResponseLoggingFilter;
import com.mockenize.filter.TextPlainMessageBodyWriter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        config();
        setupFilters();
        setupControllers();
    }

    private void config() {
        register(ExceptionMapper.class);
        register(TextPlainMessageBodyWriter.class);
    }

    private void setupControllers() {
        register(MockController.class);
        register(ManageMocksController.class);
        register(ProxyController.class);
        register(ManageProxiesController.class);
        register(LogController.class);
    }

    private void setupFilters() {
        register(CrossOriginFilter.class);
        register(RequestLoggingFilter.class);
        register(ResponseLoggingFilter.class);
    }
}
