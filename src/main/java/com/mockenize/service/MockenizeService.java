package com.mockenize.service;

import java.util.List;
import java.util.Map;
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
		int size = mockBeanList.getValues().size();
		if (size > 1) {
			mockBean = mockBeanList.getValues().get(random.nextInt(size));
		} else if (size == 0) {
			mockBean = mockBeanList;
		} else {
			mockBean = mockBeanList.getValues().get(FIRST);
		}
		return mockBean;
	}

	public void delete(List<Map<String, String>> values) {
		for(Map<String, String> map : values) {
			hazelCastService.delete(map.get("url"));
		}
	}

	public void clearAll() {
		hazelCastService.clearAll();
	}

	public void insert(MockBeanList mockBeanList) {
		hazelCastService.insert(mockBeanList.getUrl(), mockBeanList);
	}

}