package org.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 3414602294218839377L;

	public ResourceNotFoundException() {
		super("Resource not found", Response.Status.NOT_FOUND);
	}
}
