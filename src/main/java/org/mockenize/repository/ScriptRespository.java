package org.mockenize.repository;

import com.google.common.base.Strings;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.mockenize.model.ScriptBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Repository
public class ScriptRespository {

    private static final String CACHE_KEY = "scripts";

    private final IMap<String, ScriptBean> map;

    @Autowired
    public ScriptRespository(HazelcastInstance hazelcastInstance) {
        this.map = hazelcastInstance.getMap(CACHE_KEY);
    }

    public ScriptBean findByKey(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }

        return map.get(name);
    }

    public void delete(String name) {
        map.remove(name);
    }

    public void save(ScriptBean scriptBean) {
        map.put(scriptBean.getName(), scriptBean);
    }

    public Collection<ScriptBean> findAll() {
        return map.values();
    }

    public Set<String> findAllKeys() {
        return map.keySet();
    }
}
