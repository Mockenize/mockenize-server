package org.mockenize.controller;

import java.util.Collection;

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

import org.mockenize.model.MockBean;
import org.mockenize.model.MultipleMockBean;
import org.mockenize.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/_mockenize/mocks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MocksController {

	@Autowired
	private MockService mockService;

	@GET
	public Response getAll() {
		Collection<MockBean> mocks = mockService.getAll();
		return Response.ok(mocks).build();
	}

	@POST
	public Response create(MultipleMockBean mockBean) {
		MockBean createdMockBean = mockService.save(mockBean);
		return Response.status(Response.Status.CREATED).entity(createdMockBean).build();
	}

	@DELETE
	@Path("/all")
	public Response deleteAll(Collection<MockBean> mockBeans) {
		Collection<MockBean> deletedMockBeans = mockService.deleteAll(mockBeans);
		return Response.ok(deletedMockBeans).build();
	}

	@PUT
	public Response update(MultipleMockBean mockBean) {
		return create(mockBean);
	}

	@GET
	@Path("/{key}")
	public Response getById(@PathParam("key") String key) {
		MockBean mockBean = mockService.getByKey(key);
		return Response.ok(mockBean).build();
	}

	@DELETE
	public Response delete(MockBean mockBean) {
		MockBean deletedMockBean = mockService.delete(mockBean);
		return Response.ok(deletedMockBean).build();
	}
	
	@DELETE
	@Path("/{key}")
	public Response delete(@PathParam("key") String key) {
		MockBean deletedMockBean = mockService.delete(key);
		return Response.ok(deletedMockBean).build();
	}
}
