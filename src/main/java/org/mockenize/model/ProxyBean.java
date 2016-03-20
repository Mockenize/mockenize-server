package org.mockenize.model;

import java.io.Serializable;
import java.net.URI;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
public class ProxyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String _ = "_";

	private static final String SLASH = "/";

	@NotEmpty
	private String key;

	@NotEmpty
	private String path;

	@NotNull
	private String name;

	@NotNull
	private URI url;

	public String getKey() {
		return path.toLowerCase().replaceAll(SLASH, _);
	}
}
