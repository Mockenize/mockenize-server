package com.mockenize.exception;

import javax.ws.rs.WebApplicationException;

public class ValidationException extends WebApplicationException {

	private static final long serialVersionUID = 7041934368058030477L;

	public ValidationException(String error) {
		super(error);
	}

}
