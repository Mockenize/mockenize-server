package com.mockenize.model;

import java.io.Serializable;

public class JSBean implements Serializable {

	private static final long serialVersionUID = -453160748800414401L;
	private String name;
	private String value;

	public JSBean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public JSBean() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
