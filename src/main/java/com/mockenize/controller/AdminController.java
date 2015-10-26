package com.mockenize.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mockenize.model.MockBeanList;
import com.mockenize.model.ReturnBean;
import com.mockenize.service.MockenizeService;

@Path("/_mockenize")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
	public ReturnBean delete(List<Map<String, String>> values) {
		mockenizeService.delete(values);
		return new ReturnBean("Successfully removed value!");
	}

	@DELETE
	@Path("/clearAll")
	public ReturnBean clearAll() {
		mockenizeService.clearAll();
		return new ReturnBean("All values were successfully removed!");
	}

	@POST
	public ReturnBean insert(MockBeanList mockBeanList) {
		mockenizeService.insert(mockBeanList);
		return new ReturnBean("All values were successfully inserted!");
	}

}