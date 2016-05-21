package org.mockenize.repository;

import java.util.Collection;
import java.util.Iterator;

import org.mockenize.model.MockBean;
import org.mockenize.vendor.hazelcast.predicate.PathPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazelcast.core.HazelcastInstance;

@Repository
public class MockRepository extends AbstractRepository<MockBean> {

	public static final String MAP_KEY = "mocks";

	@Autowired
	public MockRepository(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance, MAP_KEY);
	}

	public MockBean findByMethodAndPath(String method, String path) {
		Collection<MockBean> values = map.values(new PathPredicate(method, path));
		Iterator<MockBean> iterator = values.iterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

}
