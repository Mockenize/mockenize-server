package org.mockenize.vendor.hazelcast.serializer;

import org.mockenize.model.LogBean;
import org.springframework.stereotype.Component;

/**
 * Created by rwatanabe on 08/02/16.
 */
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
