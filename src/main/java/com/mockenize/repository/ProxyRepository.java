package com.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicates;
import com.mockenize.model.ProxyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Repository
public class ProxyRepository {

    private final String MAP_KEY = "proxies";

    private final IMap<String, ProxyBean> map;

    @Autowired
    public ProxyRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(MAP_KEY);
        map.addIndex("key", true);
    }

    public Collection<ProxyBean> findAll() {
        return map.values();
    }

    public ProxyBean save(ProxyBean proxyBean) {
        return map.put(proxyBean.getKey(), proxyBean);
    }

    public ProxyBean findByKey(String key) {
        return map.get(key);
    }

    public void delete(String key) {
        map.remove(key);
    }

    public void deleteAll() {
        map.clear();
    }
}
