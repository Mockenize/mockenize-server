package org.mockenize.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
public class ResponseLogBean {

    private Integer status;

    private Map<String, String> headers = new HashMap<>();

    private JsonNode body;
}
