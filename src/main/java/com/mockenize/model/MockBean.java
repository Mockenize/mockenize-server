package com.mockenize.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class MockBean extends MockenizeBean {

	@NotNull
	private String key;

	@NotNull
	private String path;

	@NotNull
	private String method;

	private Map<String, String> headers = Collections.emptyMap();

	private JsonNode body;

	@NotNull
	private Integer status = 200;

	private int timeout = 0;

	private int minTimeout = 0;

	private int maxTimeout = 0;

	private String scriptName;
}
