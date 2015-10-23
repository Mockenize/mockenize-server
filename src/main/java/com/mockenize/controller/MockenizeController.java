package com.mockenize.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mockenize.model.MockBean;
import com.mockenize.service.MockenizeService;

@Path("/{regex:[^_].*}")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Service
public class MockenizeController {

	@Autowired
	private MockenizeService mockenizeService;

	@GET
	public Response get(@Context HttpServletRequest request) {
		MockBean mockBean = mockenizeService.getUrlOrId(request.getRequestURI());
		if (mockBean != null) {
			return Response.status(mockBean.getResponseCode()).entity(mockBean.getResponse()).build();
		}
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}

	@POST
	public Response post(@Context HttpServletRequest request) {
		return get(request);
	}

	@PUT
	public Response put(String body, @Context HttpServletRequest request) {
		return get(request);
	}

	@DELETE
	public Response delete(@Context HttpServletRequest request) {
		return get(request);
	}

	@HEAD
	public Response head(@Context HttpServletRequest request) {
		return get(request);
	}
}
