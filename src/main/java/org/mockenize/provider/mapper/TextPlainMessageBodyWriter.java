package org.mockenize.provider.mapper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

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
        return jsonNode.asText().length();
    }

    @Override
    public void writeTo(JsonNode jsonNode, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
        entityStream.write(jsonNode.asText().getBytes());
    }
}
