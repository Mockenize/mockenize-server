package org.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author mauricioadlima
 *
 */
public class ResourceNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = -1336666303655740602L;

	public ResourceNotFoundException(String resource) {
		super("Resource not found: " + resource, Response.Status.NOT_FOUND);
	}
}
