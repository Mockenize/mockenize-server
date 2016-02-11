package com.mockenize.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleMockBean extends MockBean {

    private List<MockBean> values = Collections.emptyList();
}