package org.mockenize.repository;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.mockenize.model.ProxyBean;
import org.mockenize.vendor.hazelcast.predicate.PathPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Repository
public class ProxyRepository {

    private static final String CACHE_KEY = "proxies";

    private final IMap<String, ProxyBean> map;

    @Autowired
    public ProxyRepository(HazelcastInstance hazelcastInstance) {
        this.map = hazelcastInstance.getMap(CACHE_KEY);
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

    public ProxyBean findByPath(String path) {
        Set<String> keys = map.keySet(new PathPredicate(path));
        Iterator<String> iterator = keys.iterator();
        return iterator.hasNext() ? map.get(iterator.next()) : null;
    }

    public boolean exists(String path) {
        return !map.keySet(new PathPredicate(path)).isEmpty();
    }
}
