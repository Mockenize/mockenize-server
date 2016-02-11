package com.mockenize.service;

import com.mockenize.model.MockBean;
import com.mockenize.model.MultipleMockBean;
import com.mockenize.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MockService implements ResponseService {

	@Autowired
	private MockRepository mockRepository;

	private Random random = new Random();

	private static final int FIRST = 0;

	public MultipleMockBean getByKey(String key) {
		return  mockRepository.findByKey(key);
	}

	public MockBean getByMethodAndPath(String method, String path) {
		MultipleMockBean multipleMockBean = mockRepository.findByMethodAndPath(method, path);
		MockBean mockBean = null;
		if (multipleMockBean != null) {
			sleep(multipleMockBean);
			int size = multipleMockBean.getValues().size();
			if (size > 1) {
				mockBean = multipleMockBean.getValues().get(random.nextInt(size));
			} else if (size == 0) {
				mockBean = multipleMockBean;
			} else {
				mockBean = multipleMockBean.getValues().get(FIRST);
			}
		}
		return mockBean;
	}

	public Collection<MultipleMockBean> getAll() {
		return mockRepository.findAll();
	}

	public MultipleMockBean save(MultipleMockBean mockBean) {
		mockBean.setKey(toKey(mockBean));
		return mockRepository.save(mockBean);
	}

	public void delete(String key) {
		mockRepository.delete(key);
	}

	public void deleteAll() {
		mockRepository.deleteAll();
	}

	@Override
	public Response getResponse(HttpServletRequest request) {
		MockBean mockBean = getByMethodAndPath(request.getMethod(), request.getRequestURI());
		Map<String, String> headers = mockBean.getHeaders();
		final ResponseBuilder responseBuilder = Response.status(mockBean.getStatus());
		for(Entry<String, String> entry : headers.entrySet()) {
			responseBuilder.header(entry.getKey(), entry.getValue());
		}
		String contentType = headers.getOrDefault(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);
		return responseBuilder.type(contentType).entity(mockBean.getBody()).build();
	}

	public Boolean exists(String method, String path) {
		return mockRepository.exists(toKey(method, path));
	}

	private String toKey(String method, String path) {
		return method.concat(path.replaceAll("/", "_"));
	}

	private String toKey(MockBean mockBean) {
		return toKey(mockBean.getMethod(), mockBean.getPath());
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
}
