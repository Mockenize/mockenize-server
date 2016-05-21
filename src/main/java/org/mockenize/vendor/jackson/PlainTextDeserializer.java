package org.mockenize.vendor.jackson;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class PlainTextDeserializer extends JsonDeserializer<JsonNode> {

    @Context
    @Autowired
    private HttpServletRequest request;

    @Override
    public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) {
        return null;
    }
}
