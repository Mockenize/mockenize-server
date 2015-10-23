package com.mockenize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockenize.model.MockBean;
import com.mockenize.model.MockBeanList;

@Service
public class MockenizeService {

	@Autowired
	private HazelcastService<MockBean> hazelCastService;

	public MockBean getUrlOrId(String urlOrId) {
		MockBean mockBean = hazelCastService.get(urlOrId);
		return mockBean;
	}

	public void delete(String urlOrId) {
		hazelCastService.get(urlOrId);
		hazelCastService.delete(urlOrId);
	}

	public void clearAll() {
		hazelCastService.clearAll();
	}

	public void insert(MockBeanList mockBeanList) {
	}

}