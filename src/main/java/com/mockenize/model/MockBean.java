package com.mockenize.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import com.mockenize.vendor.jackson.PlainTextDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MockBean {

	private static final long serialVersionUID = -3389671241241187207L;

	@NotNull
	private String key;

	@NotNull
	private String path;

	@NotNull
	private String method;

	private Map<String, String> headers = Collections.emptyMap();

	private JsonNode body;

	@NotNull
	private Integer responseCode = 200;

	private int timeout = 0;

	private int minTimeout = 0;

	private int maxTimeout = 0;
}
