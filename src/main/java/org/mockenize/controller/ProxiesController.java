package org.mockenize.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mockenize.model.ProxyBean;
import org.mockenize.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Controller
@Path("/_proxies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProxiesController {

	@Autowired
	private ProxyService proxyService;

	@GET
	public Response getAll() {
		Iterable<ProxyBean> proxys = proxyService.getAll();
		return Response.ok(proxys).build();
	}

	@POST
	public Response create(ProxyBean proxyBean) {
		proxyService.save(proxyBean);
		return Response.status(Response.Status.CREATED).entity(proxyBean).build();
	}

	@PUT
	public Response update(ProxyBean proxyBean) {
		return create(proxyBean);
	}

	@GET
	@Path("/{proxyKey}")
	public Response getById(@PathParam("proxyKey") String key) {
		ProxyBean proxyBean = proxyService.getByKey(key);
		return Response.ok(proxyBean).build();
	}

	@DELETE
	public Response delete(ProxyBean proxyBean) {
		proxyService.delete(proxyBean);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/all")
	public Response deleteAll() {
		proxyService.deleteAll();
		return Response.noContent().build();
	}
}
