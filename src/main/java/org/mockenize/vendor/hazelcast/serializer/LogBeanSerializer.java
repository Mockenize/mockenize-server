package org.mockenize.vendor.hazelcast.serializer;

import org.mockenize.model.LogBean;
import org.springframework.stereotype.Component;

@Component
public class LogBeanSerializer extends AbstractSerializer<LogBean> {

    public LogBeanSerializer() {
        super(LogBean.class);
    }

    @Override
    public int getTypeId() {
        return 2;
    }
}
