package com.mockenize.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MockBeanList extends MockBean implements Serializable {
	private static final long serialVersionUID = 1449207764832743670L;
	private String url;
	private int timeout = 0;
	
	private List<MockBean> values;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public List<MockBean> getValues() {
		return values;
	}

	public void setMockBeans(List<MockBean> values) {
		this.values = values;
	}

}
