package com.mockenize.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mockenize.model.MockBeanList;
import com.mockenize.service.MockenizeService;

@Path("/_mockenize")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
@Component
public class AdminController {

	@Autowired
	private MockenizeService mockenizeService;

	@DELETE
	@Path("/{urlOrId}")
	public Response delete(final @PathParam("urlOrId") String urlOrId) {
		mockenizeService.delete(urlOrId);
		return Response.ok().build();
	}

	@DELETE
	@Path("/clearAll")
	public Response clearAll() {
		mockenizeService.clearAll();
		return Response.ok().build();
	}

	@POST
	public Response insert(MockBeanList mockBeanList) {
		mockenizeService.insert(mockBeanList);
		return Response.ok().build();
	}

}