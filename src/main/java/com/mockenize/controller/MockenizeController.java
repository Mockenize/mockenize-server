package com.mockenize.controller;

import java.io.IOException;
import java.util.Map.Entry;

import javax.script.ScriptException;
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
import org.springframework.stereotype.Controller;

import com.google.common.io.CharStreams;
import com.mockenize.exception.JSExecutionException;
import com.mockenize.exception.ResourceNotFoundException;
import com.mockenize.model.JSBean;
import com.mockenize.model.MockBean;
import com.mockenize.service.JSService;
import com.mockenize.service.MockenizeService;

@Path("/{regex:[^_].*}")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Controller
public class MockenizeController {

	@Autowired
	private MockenizeService mockenizeService;
	
	@Autowired
	private JSService jsService;

	@GET
	public Response get(@Context HttpServletRequest request) {
		MockBean mockBean = mockenizeService.getMockBean(request.getMethod(), request.getRequestURI());
		if (mockBean != null) {
			ResponseBuilder builder = Response.status(mockBean.getResponseCode());
			addHeaders(builder, mockBean);
			String body = mockBean.getBody();
			
			if(mockBean.getJSName() != null && !mockBean.getJSName().isEmpty()) {
				JSBean jsBean = jsService.getJSBean(mockBean.getJSName());
				try {
					body = jsService.execute(jsBean, request.getRequestURI(), CharStreams.toString(request.getReader()));
				} catch (NoSuchMethodException | ScriptException | IOException e) {
					throw new JSExecutionException(e);
				}
			} 			
			
			return builder.type(getContentType(mockBean)).entity(body).build();
		}
		throw new ResourceNotFoundException();
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
