package com.mockenize.controller;

import com.mockenize.model.LogBean;
import com.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

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
}
