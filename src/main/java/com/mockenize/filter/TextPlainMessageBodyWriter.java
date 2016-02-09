package com.mockenize.filter;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Provider
@Component
@Produces(MediaType.TEXT_PLAIN)
public class TextPlainMessageBodyWriter implements MessageBodyWriter<JsonNode> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(JsonNode jsonNode, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return jsonNode.toString().length();
    }

    @Override
    public void writeTo(JsonNode jsonNode, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write(jsonNode.asText().getBytes());
    }
}
