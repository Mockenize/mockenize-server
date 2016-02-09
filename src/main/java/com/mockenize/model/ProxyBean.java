package com.mockenize.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxyBean implements Serializable {

    @NotNull
    private String key;

    @NotNull
    private String name;

    @NotNull
    private URI url;
}
