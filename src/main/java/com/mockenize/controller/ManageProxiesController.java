package com.mockenize.controller;

import com.mockenize.model.ProxyBean;
import com.mockenize.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Controller
@Path("/manage/proxies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManageProxiesController {

    @Autowired
    private ProxyService proxyService;

    @GET
    public Response getAll() {
        Iterable<ProxyBean> proxys = proxyService.getAll();
        return Response.ok(proxys).build();
    }

    @POST
    public Response create(ProxyBean proxyBean) {
        ProxyBean createdProxyBean = proxyService.save(proxyBean);
        return Response.status(Response.Status.CREATED).entity(createdProxyBean).build();
    }

    @PUT
    @Path("/{proxyKey}")
    public Response update(@PathParam("proxyKey") String key, ProxyBean proxyBean) {
        proxyService.save(proxyBean);
        return Response.noContent().build();
    }

    @GET
    @Path("/{proxyKey}")
    public Response getById(@PathParam("proxyKey") String key) {
        ProxyBean proxyBean = proxyService.getByKey(key);
        return Response.ok(proxyBean).build();
    }

    @DELETE
    @Path("/{proxyKey}")
    public Response delete(@PathParam("proxyKey") String key) {
        proxyService.delete(key);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteAll() {
        proxyService.deleteAll();
        return Response.noContent().build();
    }
}
