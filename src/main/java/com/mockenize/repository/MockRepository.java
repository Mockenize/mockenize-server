package com.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.mockenize.exception.ResourceNotFoundException;
import com.mockenize.model.MockBean;
import com.mockenize.model.MultipleMockBean;
import com.mockenize.vendor.hazelcast.predicate.RegexKeyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by rwatanabe on 05/02/16.
 */
@Repository
public class MockRepository {

    public static final String MAP_KEY = "mocks";

    private final IMap<String, MultipleMockBean> map;

    @Autowired
    public MockRepository(HazelcastInstance hazelcastInstance) {
        this.map = hazelcastInstance.getMap(MAP_KEY);
        map.addIndex("key", true);
    }

    public MultipleMockBean findByKey(String key) {
        return map.get(key);
    }

    public Collection<MultipleMockBean> findAll() {
        return map.values();
    }

    public MultipleMockBean save(MultipleMockBean mockBean) {
        map.put(mockBean.getKey(), mockBean);
        return mockBean;
    }

    public void delete(String key) {
        map.remove(key);
    }

    public void deleteAll() {
        map.clear();
    }

    public MultipleMockBean findByMethodAndPath(String method, String path) {
        Set<String> keys = map.keySet(new RegexKeyPredicate(method.concat(path.replaceAll("/", "_"))));
        Iterator<String> iterator = keys.iterator();
        return iterator.hasNext() ? map.get(iterator.next()) : null;
    }

    public boolean exists(String key) {
        return !map.keySet(new RegexKeyPredicate(key)).isEmpty();
    }
}
