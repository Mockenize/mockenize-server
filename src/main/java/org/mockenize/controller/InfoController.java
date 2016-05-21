package org.mockenize.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mockenize.MockenizeInfo;
import org.mockenize.service.LoggingService;
import org.mockenize.service.MockService;
import org.mockenize.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hazelcast.core.HazelcastInstance;

@Controller
@Path("/_mockenize")
@Produces(MediaType.APPLICATION_JSON)
public class InfoController {

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private MockService mockService;

	@Autowired
	private ScriptService scriptService;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@GET
	@Path("/info")
	public Map<String, Object> getVersion() {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("Authors", MockenizeInfo.getAuthors());
		info.put("Mockenize Version: ", MockenizeInfo.getVersion());
		info.put("Hazelcast Status:", hazelcastInstance.getLifecycleService().isRunning());
		info.put("Total Mocks:", mockService.getAll().size());
		info.put("Total Scripts:", scriptService.getAll().size());
		info.put("Total Logs:", loggingService.getAll().size());
		return info;
	}
}
