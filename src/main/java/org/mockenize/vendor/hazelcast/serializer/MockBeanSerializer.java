package org.mockenize.vendor.hazelcast.serializer;

import org.mockenize.model.MultipleMockBean;
import org.springframework.stereotype.Component;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Component
public class MockBeanSerializer extends AbstractSerializer<MultipleMockBean> {

    public MockBeanSerializer() {
        super(MultipleMockBean.class);
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
