package org.mockenize.model;

import lombok.Data;

import java.io.Serializable;

import org.mockenize.repository.Cacheable;

@Data
public class ScriptBean implements Serializable, Cacheable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String value;

	public ScriptBean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.name;
	}
}
