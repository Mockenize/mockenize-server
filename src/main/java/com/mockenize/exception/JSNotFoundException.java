package com.mockenize.exception;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class JSNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 3414602294218839377L;

	public JSNotFoundException() {
		super("JS not found", HttpStatus.NOT_FOUND_404);
	}
}
