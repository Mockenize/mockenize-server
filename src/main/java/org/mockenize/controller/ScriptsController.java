package org.mockenize.controller;

import java.util.Collection;

import javax.script.ScriptException;
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

import org.mockenize.model.ScriptBean;
import org.mockenize.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Path("/_scripts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ScriptsController {

	@Autowired
	private ScriptService scriptService;

	@GET
	@Path("/name/{scriptName}")
	public String getScript(@PathParam("scriptName") String scriptName) throws ScriptException, NoSuchMethodException {
		return scriptService.getByKey(scriptName).getValue();
	}

	@GET
	public Collection<ScriptBean> getAll() {
		return scriptService.getAll();
	}
	
	@GET
	@Path("/keys")
	public Collection<String> getAllKeys() {
		return scriptService.getAllKeys();
	}

	@DELETE
	@Path("/{scriptName}")
	public Response delete(@PathParam("scriptName") String scriptName) {
		scriptService.delete(new ScriptBean(scriptName, null));
		return Response.noContent().build();
	}

	@DELETE
	@Path("/all")
	public Response delete() {
		scriptService.deleteAll();
		return Response.noContent().build();
	}

	@POST
	@Path("/{scriptName}")
	public Response insert(@PathParam("scriptName") String scriptName, String scriptValue) {
		scriptService.save(new ScriptBean(scriptName, scriptValue));
		return Response.noContent().build();
	}

	@PUT
	@Path("/{scriptName}")
	public Response update(@PathParam("scriptName") String scriptName, String scriptValue) {
		return insert(scriptName, scriptValue);
	}

}
