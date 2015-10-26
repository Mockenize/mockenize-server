package com.mockenize.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockenize.model.MockBean;
import com.mockenize.model.MockBeanList;

import javax.ws.rs.core.Response;

@Service
public class MockenizeService {

	@Autowired
	private HazelcastService<MockBeanList> hazelCastService;

	private Random random = new Random();

	private static final int FIRST = 0;

	public MockBean getMockBean(String url) {
		MockBeanList mockBeanList = hazelCastService.get(url);
		MockBean mockBean = null;
		if (mockBeanList != null) {
			sleep(mockBeanList);
			int size = mockBeanList.getValues().size();
			if (size > 1) {
				mockBean = mockBeanList.getValues().get(random.nextInt(size));
			} else if (size == 0) {
				mockBean = mockBeanList;
			} else {
				mockBean = mockBeanList.getValues().get(FIRST);
			}
		}
		return mockBean;
	}

	private void sleep(MockBean mockBean) {
		try {
			int timeout = mockBean.getTimeout();
			if (mockBean.getMaxTimeout() > 0) {
				timeout = ThreadLocalRandom.current().nextInt(mockBean.getMinTimeout(), mockBean.getMaxTimeout());
			}
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void delete(List<Map<String, String>> values) {
		for (Map<String, String> map : values) {
			hazelCastService.delete(map.get("url"));
		}
	}

	public void clearAll() {
		hazelCastService.clearAll();
	}

	public void insert(MockBeanList mockBeanList) {
		hazelCastService.insert(mockBeanList.getUrl(), mockBeanList);
	}

	public Map<String, MockBeanList> getAllMockBeans() {
		return hazelCastService.getAll();
	}
}