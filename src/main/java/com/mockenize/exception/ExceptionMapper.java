package com.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.mockenize.model.ReturnBean;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

	private static final String CONTENT_TYPE = "Content-Type";

	@Override
	public Response toResponse(Throwable throwable) {
		throwable.printStackTrace();
		int status = HttpStatus.INTERNAL_SERVER_ERROR_500;
		if (throwable instanceof WebApplicationException) {
			status = ((WebApplicationException) throwable).getResponse().getStatus();
		}
		return Response.status(status).header(CONTENT_TYPE, MediaType.APPLICATION_JSON).entity(new ReturnBean(throwable)).build();
	}

}
