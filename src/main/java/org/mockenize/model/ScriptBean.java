package org.mockenize.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScriptBean implements Serializable {

	private String name;

	private String value;

	public ScriptBean(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
