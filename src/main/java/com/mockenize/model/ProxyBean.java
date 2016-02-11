package com.mockenize.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProxyBean extends MockenizeBean {

    @NotNull
    private String name;

    @NotNull
    private URI url;
}
