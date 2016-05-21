package org.mockenize.provider.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.mockenize.model.LogBean;
import org.mockenize.model.ResponseLogBean;
import org.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Provider
@Component
@Priority(Integer.MAX_VALUE)
public class ResponseLoggingFilter implements ContainerResponseFilter {

	public static final String KEY = "Mockenize-Id";

	private static final Joiner joiner = Joiner.on(", ");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Object key = request.getAttribute(KEY);
		if (key != null) {
			LogBean logBean = loggingService.getByKey((UUID) key);
			if (logBean != null) {
				responseContext.getHeaders().put(KEY, Lists.<Object> newArrayList(key));
				logBean.setResponse(mapResponseLogBean(responseContext));
				loggingService.save(logBean);
			}
		}
	}

	private ResponseLogBean mapResponseLogBean(ContainerResponseContext responseContext) throws IOException {
		ResponseLogBean responseLogBean = new ResponseLogBean();
		responseLogBean.setStatus(responseContext.getStatus());
		Map<String, String> headers = Maps.newHashMap();

		if (responseContext.getEntity() instanceof InputStream) {
			JsonNode responseEntity = objectMapper.readTree((InputStream) responseContext.getEntity());
			responseLogBean.setBody(responseEntity);
			responseContext.setEntity(responseEntity);
		} else {
			responseLogBean.setBody(objectMapper.convertValue(responseContext.getEntity(), JsonNode.class));
		}

		for (Map.Entry<String, List<Object>> header : responseContext.getHeaders().entrySet()) {
			headers.put(header.getKey(), joiner.join(header.getValue()));
		}

		responseLogBean.setHeaders(headers);
		return responseLogBean;
	}

}
