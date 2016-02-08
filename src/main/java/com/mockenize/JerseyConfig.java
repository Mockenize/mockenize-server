package com.mockenize;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.mockenize.controller.AdminController;
import com.mockenize.controller.FileController;
import com.mockenize.controller.JSController;
import com.mockenize.controller.MockenizeController;
import com.mockenize.exception.ExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(AdminController.class);
		register(MockenizeController.class);
		register(JSController.class);
		register(ExceptionMapper.class);
		register(FileController.class);
		register(MultiPartFeature.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

}
