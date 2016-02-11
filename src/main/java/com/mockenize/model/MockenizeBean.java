package com.mockenize.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Data
public class MockenizeBean implements Serializable {

    @NotEmpty
    private String key;

    @NotEmpty
    private String path;
}
