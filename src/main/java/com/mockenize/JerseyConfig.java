package com.mockenize;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.mockenize.controller.AdminController;
import com.mockenize.controller.MockenizeController;
import com.mockenize.exception.ExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AdminController.class);
        register(MockenizeController.class);
        register(ExceptionMapper.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
