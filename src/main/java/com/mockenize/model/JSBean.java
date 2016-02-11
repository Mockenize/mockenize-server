package com.mockenize.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JSBean extends MockenizeBean {

	private String name;

	private String value;

	public JSBean(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
