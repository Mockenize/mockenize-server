package org.mockenize.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.mockenize.service.MockenizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Controller
@Path("{path:[^_].+}")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
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
    public Response delete() {
        return mockenizeService.getResponse(request);
    }

    @HEAD
    public Response head() {
        return mockenizeService.getResponse(request);
    }

    @OPTIONS
    public Response options() {
        return mockenizeService.getResponse(request);
    }
}
