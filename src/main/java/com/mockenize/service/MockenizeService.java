package com.mockenize.service;

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

    public Response getResponse(HttpServletRequest request) {
        return buildResponse(request);
    }

    private Response buildResponse(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        boolean mockPathExists = mockService.exists(method, path);

        if (mockPathExists) {
            return mockService.getResponse(request);
        } else {
            return proxyService.getResponse(request);
        }
    }
}
