package com.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.mockenize.model.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by rwatanabe on 05/02/16.
 */
@Repository
public class MockRepository {

    public static final String MAP_KEY = "mocks";

    private final IMap<String, MockBean> map;

    @Autowired
    public MockRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(MAP_KEY);
        map.addIndex("key", true);
    }

    public MockBean findByKey(String key) {
        return map.get(key);
    }

    public Iterable<MockBean> findAll() {
        return map.values();
    }

    public MockBean save(MockBean mockBean) {
        map.put(mockBean.getKey(), mockBean);
        return mockBean;
    }

    public void delete(String key) {
        map.remove(key);
    }

    public void deleteAll() {
        map.clear();
    }
}
