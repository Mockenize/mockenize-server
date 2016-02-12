package org.mockenize.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.mockenize.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * Created by rwatanabe on 10/02/16.
 */
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

        throw new ResourceNotFoundException();
    }

    public Response getResponse(HttpServletRequest request) {
        return getResponse(request, null);
    }
}
