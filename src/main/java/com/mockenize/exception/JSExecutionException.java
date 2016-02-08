package com.mockenize.exception;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class JSExecutionException extends WebApplicationException {

	private static final long serialVersionUID = -4348584639704062868L;

	public JSExecutionException(Throwable cause) {
		super(cause, HttpStatus.INTERNAL_SERVER_ERROR_500);
	}
}
