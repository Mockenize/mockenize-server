package org.mockenize.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mockenize.service.MockenizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@Path("{path:[^_].+}")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
public class MockenizeController {

	@Autowired
	private MockenizeService mockenizeService;

	@Context
	private HttpServletRequest request;

	@GET
	public Response get() {
		return mockenizeService.getResponse(request);
	}

	@POST
	public Response post(JsonNode body) {
		return mockenizeService.getResponse(request, body);
	}

	@PUT
	public Response put(JsonNode body) {
		return mockenizeService.getResponse(request, body);
	}

	@DELETE
	public Response delete(JsonNode body) {
		return mockenizeService.getResponse(request, body);
	}

	@HEAD
	public Response head() {
		return mockenizeService.getResponse(request);
	}

	@OPTIONS
	public Response options(JsonNode body) {
		return mockenizeService.getResponse(request, body);
	}
}
