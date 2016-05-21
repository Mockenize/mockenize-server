package org.mockenize.model;

import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleMockBean extends MockBean {

	private List<MockBean> values = Collections.emptyList();

	private boolean random;

	private int lastIndexResponse = -1;

	public int nextIndex() {
		if (++lastIndexResponse >= values.size()) {
			lastIndexResponse = 0;
		}
		return lastIndexResponse;
	}

	public List<MockBean> getValues() {
		if (!values.isEmpty()) {
			for (MockBean mockBean : values) {
				mockBean.setMethod(getMethod());
				mockBean.setPath(getPath());
			}
		}
		return this.values;
	}
}