package com.mockenize.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mockenize.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mockenize.model.MockBeanList;
import com.mockenize.service.MockenizeService;

@Path("/_mockenize")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Controller
public class AdminController {

	@Autowired
	private MockenizeService mockenizeService;

	@GET
	public Response getAll() {
		Map<String, MockBeanList> mockBeanList = mockenizeService.getAllMockBeans();
		return Response.ok(mockBeanList).build();
	}

	@DELETE
	public Response delete(List<Map<String, String>> values) {
		mockenizeService.delete(values);
		Message message = new Message("Successfully removed value!");
		return Response.ok(message).build();
	}

	@DELETE
	@Path("/clearAll")
	public Response clearAll() {
		mockenizeService.clearAll();
		Message message = new Message("All values were successfully removed!");
		return Response.ok(message).build();
	}

	@POST
	public Response insert(MockBeanList mockBeanList) {
		mockenizeService.insert(mockBeanList);
		Message message = new Message("All values were successfully inserted!");
		return Response.ok(message).build();
	}

}