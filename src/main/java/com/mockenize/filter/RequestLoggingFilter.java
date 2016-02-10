package com.mockenize.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mockenize.model.LogBean;
import com.mockenize.model.LogType;
import com.mockenize.model.RequestLogBean;
import com.mockenize.model.ResponseLogBean;
import com.mockenize.service.LoggingService;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (getRequestType(requestContext.getUriInfo()).equals(LogType.NONE)) {
            return;
        }

        processRequest(requestContext);
    }

    private void processRequest(ContainerRequestContext requestContext) throws IOException {
        UUID key = UUID.randomUUID();
        MDC.put(KEY, key);
        URI requestUri = requestContext.getUriInfo().getRequestUri();
        LogBean logBean = new LogBean(key, getRequestType(requestContext.getUriInfo()));
        logBean.setUrl(requestUri.toString());
        logBean.setPath(requestUri.getPath().replaceFirst("/\\w+", ""));
        logBean.setMethod(requestContext.getMethod());
        logBean.setRequest(mapRequestLogBean(requestContext));
        loggingService.save(logBean);
    }

    private LogType getRequestType(UriInfo uriInfo) {
        String path = uriInfo.getRequestUri().getPath();

        if (path.startsWith("/api/mock")) {
            return LogType.MOCK;
        } else if (path.startsWith("/api/proxy")) {
            return LogType.PROXY;
        }

        return LogType.NONE;
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