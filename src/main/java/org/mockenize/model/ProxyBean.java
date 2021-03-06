package org.mockenize.model;

import java.io.Serializable;
import java.net.URI;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mockenize.repository.Cacheable;

import lombok.Data;

@Data
public class ProxyBean implements Serializable, Cacheable {

	private static final long serialVersionUID = 1L;

	private static final String UNDERLINE = "_";

	private static final String SLASH = "/";

	@NotEmpty
	private String key;

	@NotEmpty
	private String path;

	@NotNull
	private String name;

	@NotNull
	private URI url;

	@Override
	public String getKey() {
		return path.toLowerCase().replaceAll(SLASH, UNDERLINE);
	}
}
