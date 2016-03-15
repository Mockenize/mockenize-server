package org.mockenize.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

@Data
public class MockBean {

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

	public String getKey() {
		return method.concat(path.replaceAll("/", "_"));
	}

}
