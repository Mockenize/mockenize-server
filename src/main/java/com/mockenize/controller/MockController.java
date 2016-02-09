package com.mockenize.controller;

import com.mockenize.model.MockBean;
import com.mockenize.exception.ResourceNotFoundException;
import com.mockenize.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Map;

@Path("/mock{path:.*}")
@Controller
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
public class MockController {

	@Autowired
	private MockService mockService;

	@Context
	private HttpServletRequest request;

	@PathParam("path")
	private String path;

	@GET
	public Response get() {
		return getResponse();
	}

	@POST
	public Response post() {
		return getResponse();
	}

	@PUT
	public Response put(String body) {
		return getResponse();
	}

	@DELETE
	public Response delete() {
		return getResponse();
	}

	@HEAD
	public Response head() {
		return getResponse();
	}

	private Response getResponse() {
		MockBean mockBean = mockService.getByMethodAndPath(request.getMethod(), path);

		if (mockBean != null) {
			ResponseBuilder builder = Response.status(mockBean.getResponseCode());
			addHeaders(builder, mockBean);
			return builder.type(getContentType(mockBean)).entity(mockBean.getBody()).build();
		}

		throw new ResourceNotFoundException();
	}

	private void addHeaders(ResponseBuilder builder, MockBean mockBean) {
		if (!mockBean.getHeaders().isEmpty()) {
			for (Map.Entry<String, String> entry : mockBean.getHeaders().entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
	}

	private String getContentType(MockBean mockBean) {
		String contentType = mockBean.getHeaders().get("Content-Type");

		if (contentType == null) {
			contentType = MediaType.APPLICATION_JSON;
		}

		return contentType;
	}
}
