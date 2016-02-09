package com.mockenize.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mockenize.controller.ManageMocksController;
import com.mockenize.controller.MockController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ControllersTest.class })
@ComponentScan(basePackages = { "com.mockenize" })
public class ControllersTest {

	@Autowired
	private ManageMocksController manageMocksController;

	@Autowired
	private MockController mockController;

//	@Test
//	public void addMock() {
//		String path = "/test/200";
//		String body = "{\"msg\":\"success\"}";
//		int status = 200;
//		String contentType = "application/json";
//		String key = "X-KEY";
//		String key2 = "X-KEY";
//		String value = "X-VALUE";
//		String method = "POST";
//
//		MockBeanList mockBeanList = new MockBeanList();
//		mockBeanList.setResponseCode(status);
//		mockBeanList.setBody(body);
//		mockBeanList.setPath(path);
//		mockBeanList.setMethod(method);
//
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put(key, value);
//		headers.put(key2, value);
//		headers.put("Content-Type", contentType);
//		mockBeanList.setHeaders(headers);
//		mockManagerController.create(mockBeanList);
//
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn(path);
//		Mockito.when(request.getMethod()).thenReturn(method);
//		Response response = mockController.post(request);
//
//		assertEquals(status, response.getStatus());
//		assertEquals(contentType, response.getMediaType().toString());
//		assertEquals(body, response.getEntity());
//		assertEquals(value, response.getHeaderString(key));
//		assertEquals(value, response.getHeaderString(key2));
//	}
//
//	@Test(expected = ResourceNotFoundException.class)
//	public void deleteMock() {
//		String path = "/test/200";
//		String body = "{\"msg\":\"success\"}";
//
//		MockBeanList mockBeanList = new MockBeanList();
//		mockBeanList.setBody(body);
//		mockBeanList.setPath(path);
//		mockBeanList.setMethod("POST");
//
//		mockManagerController.create(mockBeanList);
//
//		Map<String, String> values = new HashMap<String, String>();
//		values.put("path", path);
//		mockManagerController.delete(Arrays.asList(values));
//
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn(path);
//		mockController.post(request);
//	}
//
//	@Test(expected = ResourceNotFoundException.class)
//	public void getNotFound() {
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn("/test");
//		Response response = mockController.get(request);
//
//		assertEquals(HttpStatus.NOT_FOUND_404, response.getStatus());
//	}
//
//	@Test
//	@Ignore("Ignored because this test is slow")
//	public void timeout() {
//		String path = "/test/200";
//		String body = "{\"msg\":\"success\"}";
//
//		MockBeanList mockBeanList = new MockBeanList();
//		mockBeanList.setBody(body);
//		mockBeanList.setPath(path);
//		mockBeanList.setTimeout(3);
//		mockBeanList.setMethod("POST");
//
//		mockManagerController.create(mockBeanList);
//
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn(path);
//
//		long begin = new Date().getTime();
//		mockController.post(request);
//		long end = new Date().getTime();
//
//		assertEquals((end - begin) / 1000, 3);
//	}
//
//	@Test
//	@Ignore("Ignored because this test is slow")
//	public void timeoutInterval() {
//		String path = "/test/200";
//		String body = "{\"msg\":\"success\"}";
//
//		MockBeanList mockBeanList = new MockBeanList();
//		mockBeanList.setBody(body);
//		mockBeanList.setPath(path);
//		mockBeanList.setMinTimeout(2);
//		mockBeanList.setMaxTimeout(4);
//		mockBeanList.setMethod("POST");
//
//		mockManagerController.create(mockBeanList);
//
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn(path);
//
//		long begin = new Date().getTime();
//		mockController.post(request);
//		long end = new Date().getTime();
//		int result = (int) ((end - begin) / 1000);
//		assertTrue("Result: " + result, result >= 2 && result <= 4);
//	}
//
//	@Test
//	public void sameUrlAndDifferentMethod() {
//		String path = "/test/200";
//		String body = "{\"msg\":\"success\"}";
//		int status = 200;
//		String contentType = "application/json";
//		String method = "POST";
//
//		MockBeanList mockBeanList = new MockBeanList();
//		mockBeanList.setResponseCode(status);
//		mockBeanList.setBody(body);
//		mockBeanList.setPath(path);
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", contentType);
//		mockBeanList.setHeaders(headers);
//		mockBeanList.setMethod(method);
//
//		mockManagerController.create(mockBeanList);
//
//		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//		Mockito.when(request.getRequestURI()).thenReturn(path);
//		Mockito.when(request.getMethod()).thenReturn(method);
//
//		Response response = mockController.post(request);
//		assertEquals(status, response.getStatus());
//		assertEquals(contentType, response.getMediaType().toString());
//		assertEquals(body, response.getEntity());
//
//		Mockito.when(request.getMethod()).thenReturn("GET");
//		try {
//			mockController.get(request);
//		} catch (WebApplicationException e) {
//			status = e.getResponse().getStatus();
//		}
//		assertEquals(HttpStatus.NOT_FOUND_404, status);
//	}
//
//	@Test(expected = ValidationException.class)
//	public void validation() {
//		MockBeanList mockBeanList = new MockBeanList();
//		mockManagerController.create(mockBeanList);
//	}

}
