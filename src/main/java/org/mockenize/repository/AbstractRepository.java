package org.mockenize.repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.mockenize.vendor.hazelcast.predicate.PathPredicate;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public abstract class AbstractRepository<T extends Cacheable> {

	protected final IMap<String, T> map;

	public AbstractRepository(HazelcastInstance hazelcastInstance, String mapKey) {
		this.map = hazelcastInstance.getMap(mapKey);
		map.addIndex("key", Boolean.TRUE);
	}

	public T findByKey(String key) {
		Set<String> keys = map.keySet(new PathPredicate(key));
		Iterator<String> iterator = keys.iterator();
		return iterator.hasNext() ? map.get(iterator.next()) : null;
	}

	public Collection<T> findAll() {
		return map.values();
	}

	public Collection<String> findAllKeys() {
		return Collections2.transform(map.values(), new Function<Cacheable, String>() {

			@Override
			public String apply(Cacheable cacheable) {
				return cacheable.getKey();
			}

		});
	}

	public void save(T t) {
		map.put(t.getKey(), t);
	}

	public void save(Collection<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	public T delete(String key) {
		return map.remove(key);
	}
	
	public T delete(T t) {
		return map.remove(t.getKey());
	}

	public Collection<T> deleteAll() {
		Collection<T> values = findAll();
		map.clear();
		return values;		
	}

	public boolean exists(String key) {
		return !map.keySet(new PathPredicate(key.toString())).isEmpty();
	}

}