package org.mockenize.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
public class ProxyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
    private String key;

    @NotEmpty
    private String path;

    @NotNull
    private String name;

    @NotNull
    private URI url;
}
