package com.mockenize.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockenize.model.MockBean;
import com.mockenize.model.MockBeanList;

@Service
public class MockenizeService {

	@Autowired
	private HazelcastService<MockBeanList> hazelCastService;

	private Random random = new Random();

	private static final int FIRST = 0;

	public MockBean getUrlOrId(String urlOrId) {
		MockBeanList mockBeanList = hazelCastService.get(urlOrId);
		MockBean mockBean = null;
		int size = mockBeanList.getMockBeans().size();
		if (size > 1) {
			mockBean = mockBeanList.getMockBeans().get(random.nextInt(size));
		} else if (size == 0) {
			mockBean = mockBeanList;
		} else {
			mockBean = mockBeanList.getMockBeans().get(FIRST);
		}
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
		hazelCastService.insert(mockBeanList.getUrl(), mockBeanList);
	}

}