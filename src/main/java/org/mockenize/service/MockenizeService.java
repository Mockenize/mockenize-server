package org.mockenize.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.mockenize.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class MockenizeService {

	@Autowired
	private MockService mockService;

	@Autowired
	private ProxyService proxyService;

	public Response getResponse(HttpServletRequest request, JsonNode body) {
		String path = request.getRequestURI();
		String method = request.getMethod();

		if (mockService.exists(method, path)) {
			return mockService.getResponse(request, body);
		} else if (proxyService.exists(path)) {
			return proxyService.getResponse(request, body);
		}

		throw new ResourceNotFoundException(path);
	}

	public Response getResponse(HttpServletRequest request) {
		return getResponse(request, null);
	}
}
