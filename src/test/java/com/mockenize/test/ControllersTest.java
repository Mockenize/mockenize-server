package com.mockenize.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mockenize.controller.AdminController;
import com.mockenize.controller.MockenizeController;
import com.mockenize.model.MockBeanList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ControllersTest.class })
@ComponentScan(basePackages = { "com.mockenize" })
public class ControllersTest {

	@Autowired
	private AdminController adminController;

	@Autowired
	private MockenizeController mockenizeController;

	@Test
	public void addMock() {
		String url = "/test/200";
		String body = "{\"msg\":\"success\"}";
		int status = 200;
		String contentType = "application/json";

		MockBeanList mockBeanList = new MockBeanList();
		mockBeanList.setResponseCode(status);
		mockBeanList.setResponse(body);
		mockBeanList.setUrl(url);
		mockBeanList.setContentType(contentType);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", contentType);
		mockBeanList.setHeaders(headers);
		adminController.insert(mockBeanList);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getRequestURI()).thenReturn(url);
		Response response = mockenizeController.get(request);
		
		assertEquals(status, response.getStatus());
		assertEquals(contentType, response.getMediaType().toString());
		assertEquals(body, response.getEntity());
	}
}
