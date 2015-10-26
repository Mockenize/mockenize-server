package com.mockenize.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public Map<String, MockBeanList> getAll() {
		return mockenizeService.getAllMockBeans();
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