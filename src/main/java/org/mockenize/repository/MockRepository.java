package org.mockenize.repository;

import java.util.Collection;
import java.util.Iterator;

import org.mockenize.model.MultipleMockBean;
import org.mockenize.vendor.hazelcast.predicate.PathPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;

/**
 * Created by rwatanabe on 05/02/16.
 */
@Repository
public class MockRepository extends AbstractRepository<MultipleMockBean> {

	public static final String MAP_KEY = "mocks";

	@Autowired
	public MockRepository(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance, MAP_KEY);
	}

	public MultipleMockBean findByMethodAndPath(String method, String path) {
		Collection<MultipleMockBean> values = map.values(new PathPredicate(method, path));
		Iterator<MultipleMockBean> iterator = values.iterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

}
