package org.mockenize.provider.mapper;

import org.mockenize.model.ReturnBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionMapper.class);

	private static final String CONTENT_TYPE = "Content-Type";

	@Override
	public Response toResponse(Throwable throwable) {
		LOGGER.error(throwable.getMessage(), throwable);
		int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		if (throwable instanceof WebApplicationException) {
			status = ((WebApplicationException) throwable).getResponse().getStatus();
		}
		return Response.status(status).header(CONTENT_TYPE, MediaType.APPLICATION_JSON).entity(new ReturnBean(throwable)).build();
	}

}
