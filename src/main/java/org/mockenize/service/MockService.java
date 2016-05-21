package org.mockenize.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.mockenize.model.MockBean;
import org.mockenize.model.MultipleMockBean;
import org.mockenize.model.ScriptBean;
import org.mockenize.repository.MockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;

@Service
public class MockService implements ResponseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MockService.class);

	private static final int FIRST = 0;

	@Autowired
	private MockRepository mockRepository;

	@Autowired
	private ScriptService scriptService;

	private Random random = new Random();

	public MockBean getByKey(String key) {
		return mockRepository.findByKey(key);
	}

	public MockBean getByMethodAndPath(String method, String path) {
		MockBean mockBean = mockRepository.findByMethodAndPath(method, path);

		if (mockBean instanceof MultipleMockBean) {
			MultipleMockBean multipleMockBean = (MultipleMockBean) mockBean;
			int size = multipleMockBean.getValues().size();
			if (size > 1) {
				int index;
				if (multipleMockBean.isRandom()) {
					index = random.nextInt(size);
				} else {
					index = multipleMockBean.nextIndex();
					mockRepository.save(mockBean);
				}
				mockBean = multipleMockBean.getValues().get(index);
			} else if (!multipleMockBean.getValues().isEmpty()) {
				mockBean = multipleMockBean.getValues().get(FIRST);
			}
		}

		sleep(mockBean);
		return mockBean;
	}

	public Collection<MockBean> getAll() {
		return mockRepository.findAll();
	}

	public MultipleMockBean save(MultipleMockBean mockBean) {
		mockRepository.save(mockBean);
		return mockBean;
	}

	public Collection<MockBean> deleteAll(Collection<MockBean> mockBeans) {
		Collection<MockBean> deletedMocks = new ArrayList<>();
		if (mockBeans != null && !mockBeans.isEmpty()) {
			for (MockBean bean : mockBeans) {
				MockBean deleted = mockRepository.delete(bean);
				if (deleted != null) {
					deletedMocks.add(deleted);
				}
			}
		} else {
			deletedMocks = mockRepository.deleteAll();
		}
		return deletedMocks;
	}

	public MockBean delete(MockBean mockBean) {
		return mockRepository.delete(mockBean);
	}

	public MockBean delete(String key) {
		return mockRepository.delete(key);
	}

	public Boolean exists(String method, String path) {
		return mockRepository.exists(toKey(method, path));
	}

	@Override
	public Response getResponse(HttpServletRequest request, JsonNode requestBody) {
		MockBean mockBean = getByMethodAndPath(request.getMethod(), request.getRequestURI());
		Map<String, String> headers = mockBean.getHeaders();
		final ResponseBuilder responseBuilder = Response.status(mockBean.getStatus());
		for (Entry<String, String> entry : headers.entrySet()) {
			responseBuilder.header(entry.getKey(), entry.getValue());
		}
		String contentType = headers.getOrDefault(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);
		JsonNode responseBody = getResponseBody(mockBean, request.getRequestURI(), requestBody);
		return responseBuilder.type(contentType).entity(responseBody).build();
	}

	private JsonNode getResponseBody(MockBean mockBean, String path, JsonNode body) {
		if (!Strings.isNullOrEmpty(mockBean.getScriptName())) {
			ScriptBean scriptBean = scriptService.getByKey(mockBean.getScriptName());
			return scriptService.execute(scriptBean, path, body);
		}
		return mockBean.getBody();
	}

	private String toKey(String method, String path) {
		return method.concat(path.replaceAll("/", "_"));
	}

	private void sleep(MockBean mockBean) {
		try {
			int timeout = mockBean.getTimeout();
			if (timeout > 0 || mockBean.getMaxTimeout() > 0) {
				if (mockBean.getMaxTimeout() > 0) {
					timeout = ThreadLocalRandom.current().nextInt(mockBean.getMinTimeout(), mockBean.getMaxTimeout());
				}
				Thread.sleep(Long.valueOf(timeout) * 1000);
			}
		} catch (InterruptedException e) {
			LOGGER.error("Thread woke", e);
		}
	}

}
