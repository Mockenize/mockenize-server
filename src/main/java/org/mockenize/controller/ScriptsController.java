package org.mockenize.controller;

import org.mockenize.model.ReturnBean;
import org.mockenize.model.ScriptBean;
import org.mockenize.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.script.ScriptException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.ResourceBundle;

@Path("/_scripts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ScriptsController {

	@Autowired
	private ScriptService scriptService;
	
	@Autowired
	private ResourceBundle bundle;

	@GET
	@Path("/name/{scriptName}")
	public String getScript(@PathParam("scriptName") String scriptName) throws ScriptException, NoSuchMethodException {
		return scriptService.getByKey(scriptName).getValue();
	}
	
	@GET
	@Path("/all")
	public Collection<String> getAll() {
		return scriptService.getAllKeys();
	}

	@DELETE
	@Path("/{scriptName}")
	public ReturnBean delete(@PathParam("scriptName") String scriptName) {
		scriptService.delete(new ScriptBean(scriptName, null));
		return new ReturnBean(bundle.getString("js.delete"));
	}

	@POST
	@Path("/{scriptName}")
	public ReturnBean insert(@PathParam("scriptName") String scriptName, String scriptValue) {
		scriptService.save(new ScriptBean(scriptName, scriptValue));
		return new ReturnBean(bundle.getString("js.insert"));
	}

	@PUT
	@Path("/{scriptName}")
	public ReturnBean update(@PathParam("scriptName") String scriptName, String scriptValue) {
		insert(scriptName, scriptValue);
		return new ReturnBean(bundle.getString("js.update"));
	}

}
