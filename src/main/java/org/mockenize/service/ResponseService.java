package org.mockenize.service;

import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public interface ResponseService {
    Response getResponse(HttpServletRequest request, JsonNode requestBody);
}
