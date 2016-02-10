package com.mockenize.controller;

import com.mockenize.model.LogType;
import com.mockenize.model.ProxyBean;
import com.mockenize.service.LoggingService;
import com.mockenize.service.ProxyService;
import org.glassfish.jersey.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Controller
@Path("/proxy/{name:[^/]+}{target:.*}")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
public class ProxyController {

    private Client client = ClientBuilder.newClient();

    @Autowired
    private ProxyService proxyService;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpHeaders headers;

    @PathParam("name")
    private String name;

    @PathParam("target")
    private String target;

    @GET
    public Response get() {
        Response clientResponse = requestBuilder().get();
        clientResponse.bufferEntity();

        return Response.status(clientResponse.getStatus()).entity(clientResponse.getEntity()).build();
    }

    @POST
    public Response post(String body) {
        return requestBuilder().post(Entity.entity(body, request.getContentType()));
    }

    @PUT
    public Response put(String body) {
        return requestBuilder().put(Entity.entity(body, request.getContentType()));
    }

    @DELETE
    public Response delete() {
        return requestBuilder().delete();
    }

    @HEAD
    public Response head() {
        return requestBuilder().head();
    }

    private Invocation.Builder requestBuilder() {
        ProxyBean proxyBean =  proxyService.getByKey(name);
        return client.target(proxyBean.getUrl()).path(target).request();
    }
}
