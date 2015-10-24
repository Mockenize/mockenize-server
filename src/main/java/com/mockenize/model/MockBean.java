package com.mockenize.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MockBean implements Serializable {

	private static final long serialVersionUID = -3389671241241187207L;
	private String body;
	private Integer responseCode = 200;
	private String url;
	private String method;
	private Map<String, String> headers = Collections.emptyMap();
	private int timeout = 0;
	private int minTimeout = 0;
	private int maxTimeout = 0;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMinTimeout() {
		return minTimeout;
	}

	public void setMinTimeout(int minTimeout) {
		this.minTimeout = minTimeout;
	}

	public int getMaxTimeout() {
		return maxTimeout;
	}

	public void setMaxTimeout(int maxTimeout) {
		this.maxTimeout = maxTimeout;
	}

}
