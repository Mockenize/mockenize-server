package com.mockenize.vendor.hazelcast;

import com.mockenize.model.MockBean;
import org.springframework.stereotype.Component;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Component
public class MockBeanSerializer extends AbstractSerializer<MockBean> {

    public MockBeanSerializer() {
        super(MockBean.class);
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
