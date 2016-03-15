package org.mockenize.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScriptBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String value;

	public ScriptBean(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
