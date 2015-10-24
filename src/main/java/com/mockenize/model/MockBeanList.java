package com.mockenize.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MockBeanList extends MockBean implements Serializable {

	private List<MockBean> values = Collections.emptyList();

	public List<MockBean> getValues() {
		return values;
	}

	public void setMockBeans(List<MockBean> values) {
		this.values = values;
	}

}
