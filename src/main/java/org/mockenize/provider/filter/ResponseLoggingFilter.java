package org.mockenize.provider.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jboss.logging.MDC;
import org.mockenize.model.LogBean;
import org.mockenize.model.ResponseLogBean;
import org.mockenize.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
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

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (getRequestId() == null) {
            return;
        }

        processResponse(responseContext);
    }

    private void processResponse(ContainerResponseContext responseContext) throws IOException {
        UUID key = getRequestId();
        LogBean logBean = loggingService.getByKey(key);
        responseContext.getHeaders().put(KEY, Lists.<Object>newArrayList(key));
        logBean.setResponse(mapResponseLogBean(responseContext));
        loggingService.save(logBean);
        MDC.remove(KEY);
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

    private UUID getRequestId() {
        Object key = MDC.get(KEY);

        if (key == null) {
            return null;
        }

        return UUID.fromString((String) key);
    }
}
