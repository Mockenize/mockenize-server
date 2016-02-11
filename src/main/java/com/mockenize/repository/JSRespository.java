package com.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.mockenize.model.JSBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Repository
public class JSRespository {

    private static final String CACHE_KEY = "scripts";

    private final IMap<String, JSBean> map;

    @Autowired
    public JSRespository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(CACHE_KEY);
    }

    public JSBean findByKey(String jsName) {
        return map.get(jsName);
    }

    public void delete(String name) {
        map.remove(name);
    }

    public void save(JSBean jsBean) {
        map.put(jsBean.getName(), jsBean);
    }

    public Collection<JSBean> findAll() {
        return map.values();
    }

    public Set<String> findAllKeys() {
        return map.keySet();
    }
}
