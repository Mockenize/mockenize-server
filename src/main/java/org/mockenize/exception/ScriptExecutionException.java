package org.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ScriptExecutionException extends WebApplicationException {

	private static final long serialVersionUID = -4348584639704062868L;

	public ScriptExecutionException(Throwable cause) {
		super(cause, Response.Status.NOT_FOUND);
	}
}
