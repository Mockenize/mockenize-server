package org.mockenize.provider.filter;

import com.google.common.base.Joiner;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

/**
 * Created by rwatanabe on 05/02/16.
 */

public class CrossOriginFilter implements ContainerResponseFilter {

    private static final Joiner JOINER = Joiner.on(", ");

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "HEAD,DELETE,GET,OPTIONS,PUT");
        headers.add("Access-Control-Allow-Headers", "Content-type");
    }
}
