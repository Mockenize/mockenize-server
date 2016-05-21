package org.mockenize.repository;

import org.mockenize.model.ProxyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;

@Repository
public class ProxyRepository extends AbstractRepository<ProxyBean> {

	private static final String CACHE_KEY = "proxies";

	@Autowired
	public ProxyRepository(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance, CACHE_KEY);
	}

}
