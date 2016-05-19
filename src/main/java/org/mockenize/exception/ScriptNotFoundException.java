package org.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ScriptNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 3414602294218839377L;

	public ScriptNotFoundException(String scriptName) {
		super("JS not found: " + scriptName, Response.Status.NOT_FOUND);
	}
}
