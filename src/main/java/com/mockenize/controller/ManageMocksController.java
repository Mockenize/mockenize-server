package com.mockenize.controller;

import com.mockenize.model.MockBean;
import com.mockenize.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/manage/mocks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManageMocksController {

	@Autowired
	private MockService mockService;

	@GET
	public Response getAll() {
		Iterable<MockBean> mocks = mockService.getAll();
		return Response.ok(mocks).build();
	}

	@POST
	public Response create(MockBean mockBean) {
		MockBean createdMockBean = mockService.save(mockBean);
		return Response.status(Response.Status.CREATED).entity(createdMockBean).build();
	}

	@DELETE
	public Response deleteAll() {
		mockService.deleteAll();
		return Response.noContent().build();
	}

	@PUT
	@Path("/{key}")
	public Response update(@PathParam("key") String key, MockBean mockBean) {
		mockBean.setKey(key);
		mockService.save(mockBean);
		return Response.noContent().build();
	}

	@GET
	@Path("/{key}")
	public Response getById(@PathParam("key") String key) {
		MockBean mockBean = mockService.getByKey(key);
		return Response.ok(mockBean).build();
	}

	@DELETE
	@Path("/{key}")
	public Response delete(@PathParam("key") String key) {
		mockService.delete(key);
		return Response.noContent().build();
	}
}
