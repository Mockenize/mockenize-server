package org.mockenize.controller;

import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mockenize.model.LogBean;
import org.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Controller
@Path("/_mockenize/logs")
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
    
    @POST
    @Path("find")
    public Response findByFilters(JsonNode jsonNode) {
    	Collection<LogBean> logBeans = loggingService.findByFilters(jsonNode);
    	return Response.ok(logBeans).build();
    }

    @DELETE
    @Path("/{key}")
    public Response delete(@PathParam("key") UUID key) {
        LogBean deletedLogBean = loggingService.delete(key);
        return Response.ok(deletedLogBean).build();
    }

    @DELETE
    @Path("/all")
    public Response deleteAll() {
    	Collection<LogBean> deletedLogBeans = loggingService.deleteAll();
        return Response.ok(deletedLogBeans).build();
    }
}
