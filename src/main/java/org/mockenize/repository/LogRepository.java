package org.mockenize.repository;

import org.mockenize.model.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Repository
public class LogRepository extends AbstractRepository<LogBean> {

	private static final String CACHE_KEY = "logs";

	@Autowired
	public LogRepository(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance, CACHE_KEY);
		map.addIndex("key", false);
		map.addIndex("date", true);
	}

}
