package com.mockenize.controller;

import com.mockenize.model.LogBean;
import com.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Controller
@Path("/logs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogController {

    @Autowired
    private LoggingService loggingService;

    @GET
    public Response getAll() {
        Collection<LogBean> logs = loggingService.getAll();
        return Response.ok(logs).build();
    }

    @GET
    @Path("/{key}")
    public Response getByKey(@PathParam("key") UUID key) {
        LogBean logBean = loggingService.getByKey(key);
        return Response.ok(logBean).build();
    }

    @DELETE
    @Path("/{key}")
    public Response delete(@PathParam("key") UUID key) {
        loggingService.delete(key);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteAll() {
        loggingService.deleteAll();
        return Response.noContent().build();
    }
}