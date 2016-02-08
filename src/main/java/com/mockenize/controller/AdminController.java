package com.mockenize.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.io.ByteStreams;
import com.mockenize.model.MockBeanList;
import com.mockenize.model.ReturnBean;
import com.mockenize.service.MockenizeService;

@Path("/_mockenize")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class AdminController {

	@Autowired
	private MockenizeService mockenizeService;
	
	@Autowired
	private ResourceBundle bundle;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response redirectToAdmin() throws IOException {
		URI uri = UriBuilder.fromPath("/_mockenize/index.html").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path( "/{path: .*\\.(html|css|js|woff|woff2|ttf)}")
	public Response serveStatic(@PathParam("path") String path) throws IOException, URISyntaxException {
		InputStream inputStream = getClass().getResourceAsStream("/webapp/" + path);
		byte[] content = ByteStreams.toByteArray(inputStream);

		return Response.ok(content)
			.header(HttpHeaders.CONTENT_TYPE, getContentType(path))
			.header(HttpHeaders.CONTENT_LENGTH, content.length)
			.build();
	}

	@GET
	public Map<String, MockBeanList> getAll() {
		return mockenizeService.getAllMockBeans();
	}
	
	@DELETE
	public ReturnBean delete(List<Map<String, String>> values) {
		mockenizeService.delete(values);
		return new ReturnBean(bundle.getString("mock.delete"));
	}

	@DELETE
	@Path("/clearAll")
	public ReturnBean clearAll() {
		mockenizeService.clearAll();
		return new ReturnBean(bundle.getString("mock.delete.all"));
	}

	@POST
	public ReturnBean insert(MockBeanList mockBeanList) {
		mockenizeService.insert(mockBeanList);
		return new ReturnBean(bundle.getString("mock.insert"));
	}

	private String getContentType(@PathParam("path") String path) {
		String extension = path.substring(path.lastIndexOf(".") + 1);

		switch (extension) {

			case "html":
				return "text/html";

			case "css":
				return "text/css";

			case "js":
				return "text/javascript";

			case "woff":
				return "application/font-woff";

			case "woff2":
				return "application/font-woff2";

			case "ttf":
				return "application/x-font-ttf";
		}

		return "text/plain";
	}

}
