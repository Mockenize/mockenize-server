package org.mockenize.vendor.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Component
public class PlainTextDeserializer extends JsonDeserializer<JsonNode> {

    @Context
    @Autowired
    private HttpServletRequest request;

    @Override
    public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        return null;
    }
}
