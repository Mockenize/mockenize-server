package org.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.mockenize.model.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Repository
public class RequestLogRepository {

    private final String CACHE_KEY = "request-log";

    private final IQueue<Object> queue;

    @Autowired
    public RequestLogRepository(HazelcastInstance hazelcastInstance) {
        queue = hazelcastInstance.getQueue(CACHE_KEY);
    }

    public void save(LogBean requestLogBean) {
        queue.add(requestLogBean);
    }
}
