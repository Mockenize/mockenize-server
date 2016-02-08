package com.mockenize.controller;

import java.util.Collection;
import java.util.ResourceBundle;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mockenize.model.JSBean;
import com.mockenize.model.ReturnBean;
import com.mockenize.service.JSService;

@Path("/_mockenize/js")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class JSController {

	@Autowired
	private JSService jsService;
	
	@Autowired
	private ResourceBundle bundle;

	@GET
	@Path("/name/{scriptName}")
	public String getScript(@PathParam("scriptName") String scriptName) throws ScriptException, NoSuchMethodException {
		return jsService.getJSBean(new JSBean(scriptName, null)).getValue();
	}
	
	@GET
	@Path("/all")
	public Collection<String> getAll() {
		return jsService.getJSBeans().keySet();
	}

	@DELETE
	@Path("/{scriptName}")
	public ReturnBean delete(@PathParam("scriptName") String scriptName) {
		jsService.delete(new JSBean(scriptName, null));
		return new ReturnBean(bundle.getString("js.delete"));
	}

	@POST
	@Path("/{scriptName}")
	public ReturnBean insert(@PathParam("scriptName") String scriptName, String scriptValue) {
		jsService.insert(new JSBean(scriptName, scriptValue));
		return new ReturnBean(bundle.getString("js.insert"));
	}

	@PUT
	@Path("/{scriptName}")
	public ReturnBean update(@PathParam("scriptName") String scriptName, String scriptValue) {
		insert(scriptName, scriptValue);
		return new ReturnBean(bundle.getString("js.update"));
	}

}
