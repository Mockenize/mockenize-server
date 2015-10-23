package com.mockenize.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;

@Service
public class HazelcastService<T> {

	@Autowired
	private HazelcastInstance instance;

	private static final String CACHE_KEY = "cache-key";

	public T get(final String key) {
		final Map<String, T> cache = instance.getMap(CACHE_KEY);
		return cache.get(key);
	}

	public void insert(final String key, final T t) {
		final Map<Object, Object> cache = instance.getMap(CACHE_KEY);
		cache.put(key, t);
	}

	public void delete(Object id) {
		final Map<Object, Object> cache = instance.getMap(CACHE_KEY);
		cache.remove(id);
	}

	public void clearAll() {
		final Map<Object, Object> cache = instance.getMap(CACHE_KEY);
		cache.clear();
	}

}