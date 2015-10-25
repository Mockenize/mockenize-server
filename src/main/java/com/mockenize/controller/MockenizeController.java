package com.mockenize.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.mockenize.model.MockBean;
import com.mockenize.service.MockenizeService;

@Path("/{regex:[^_].*}")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Controller
public class MockenizeController {

	@Autowired
	private MockenizeService mockenizeService;

	@GET
	public Response get(@Context HttpServletRequest request) {
		MockBean mockBean = mockenizeService.getMockBean(request.getMethod(), request.getRequestURI());
		if (mockBean != null) {
			ResponseBuilder builder = Response.status(mockBean.getResponseCode());
			addHeaders(builder, mockBean);
			return builder.type(getContentType(mockBean)).entity(mockBean.getBody()).build();
		}
		return Response.status(HttpStatus.NOT_FOUND.value()).build();
	}

	private void addHeaders(ResponseBuilder builder, MockBean mockBean) {
		if (!mockBean.getHeaders().isEmpty()) {
			for (Entry<String, String> entry : mockBean.getHeaders().entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
	}

	private String getContentType(MockBean mockBean) {
		String contentType = mockBean.getHeaders().get("Content-Type");
		if (contentType == null) {
			contentType = MediaType.TEXT_PLAIN;
		}
		return contentType;
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
