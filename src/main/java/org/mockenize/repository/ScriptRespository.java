package org.mockenize.repository;

import org.mockenize.model.ScriptBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;

/**
 * Created by rwatanabe on 10/02/16.
 */
@Repository
public class ScriptRespository extends AbstractRepository<ScriptBean> {

	private static final String CACHE_KEY = "scripts";

	@Autowired
	public ScriptRespository(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance, CACHE_KEY);
	}

}
