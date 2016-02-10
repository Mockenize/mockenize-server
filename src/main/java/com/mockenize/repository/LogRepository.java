package com.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.mockenize.model.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Repository
public class LogRepository {

    private final String CACHE_KEY = "logs";

    private final IMap<UUID, LogBean> map;

    @Autowired
    public LogRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(CACHE_KEY);
        map.addIndex("key", false);
        map.addIndex("date", true);
    }

    public void save(LogBean logBean) {
        map.put(logBean.getKey(), logBean);
    }

    public Collection<LogBean> findAll() {
        return map.values();
    }

    public LogBean findByKey(UUID key) {
        return map.get(key);
    }

    public void delete(UUID key) {
        map.remove(key);
    }

    public void deleteAll() {
        map.clear();
    }
}
