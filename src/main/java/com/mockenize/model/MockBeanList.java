package com.mockenize.model;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MockBeanList implements Serializable {
	private static final long serialVersionUID = 1782233209622249291L;
	private String url;
	private int timeout = 0;
	private Collection<MockBean> mockBeans;

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

	public Collection<MockBean> getMockBeans() {
		return mockBeans;
	}

	public void setMockBeans(Collection<MockBean> mockBeans) {
		this.mockBeans = mockBeans;
	}

}
