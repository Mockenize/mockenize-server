package org.mockenize.provider.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mockenize.model.ReturnBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionMapperImpl implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapperImpl.class);

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
