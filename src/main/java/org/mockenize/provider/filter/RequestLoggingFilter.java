package org.mockenize.provider.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.mockenize.model.LogBean;
import org.mockenize.model.LogType;
import org.mockenize.model.RequestLogBean;
import org.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Provider
@Component
@Priority(Integer.MIN_VALUE)
public class RequestLoggingFilter implements ContainerRequestFilter {

	public static final String KEY = "Mockenize-Id";

	private static final Joiner joiner = Joiner.on(", ");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LogType logType = getRequestType(requestContext.getUriInfo());
		if (LogType.MOCK.equals(logType)) {
			UUID key = UUID.randomUUID();
			request.setAttribute(KEY, key);
			URI requestUri = requestContext.getUriInfo().getRequestUri();
			LogBean logBean = new LogBean(key, getRequestType(requestContext.getUriInfo()));
			logBean.setUrl(requestUri.toString());
			logBean.setPath(requestUri.getPath());
			logBean.setMethod(requestContext.getMethod());
			logBean.setRequest(mapRequestLogBean(requestContext));
			loggingService.save(logBean);
		}
	}

	private LogType getRequestType(UriInfo uriInfo) {
		String path = uriInfo.getRequestUri().getPath();

		if (path.startsWith("/_")) {
			return LogType.NONE;
		}

		return LogType.MOCK;
	}

	private RequestLogBean mapRequestLogBean(ContainerRequestContext requestContext) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ReaderWriter.writeTo(requestContext.getEntityStream(), outputStream);
		byte[] requestEntity = outputStream.toByteArray();
		requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

		RequestLogBean requestLogBean = new RequestLogBean();
		Map<String, String> headers = Maps.newHashMap();

		for (Map.Entry<String, List<String>> header : requestContext.getHeaders().entrySet()) {
			headers.put(header.getKey(), joiner.join(header.getValue()));
		}

		if (requestEntity.length > 0) {
			requestLogBean.setBody(objectMapper.readTree(requestEntity));
		}

		requestLogBean.setHeaders(headers);
		return requestLogBean;
	}
}
