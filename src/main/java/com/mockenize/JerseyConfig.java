package com.mockenize;

import org.glassfish.jersey.server.ResourceConfig;
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
    }
}