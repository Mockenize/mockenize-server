package com.mockenize.service;

import com.mockenize.model.MockBean;
import com.mockenize.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockService {

	@Autowired
	private MockRepository mockRepository;

	private Random random = new Random();

	public MockBean getByKey(String key) {
		return mockRepository.findByKey(key);
	}

	public MockBean getByMethodAndPath(String method, String path) {
		return getByKey(toKey(method, path));
	}

	public Iterable<MockBean> getAll() {
		return mockRepository.findAll();
	}

	public MockBean save(MockBean mockBean) {
		mockBean.setKey(toKey(mockBean));
		return mockRepository.save(mockBean);
	}

	public Iterable<MockBean> save(Iterable<MockBean> mocks) {
		for (MockBean mockBean : mocks) {
			mockRepository.save(mockBean);
		}

		return mocks;
	}

	public void delete(String key) {
		mockRepository.delete(key);
	}

	public void deleteAll() {
		mockRepository.deleteAll();
	}

	private String toKey(String method, String path) {
		return method.concat(path.replaceAll("/", "-"));
	}

	private String toKey(MockBean mockBean) {
		return toKey(mockBean.getMethod(), mockBean.getPath());
	}
}
