package org.mockenize.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.mockenize.model.MultipleMockBean;
import org.mockenize.service.FileService;
import org.mockenize.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/_mockenize/file")
@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	private MockService mockService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/upload")
	public Collection<MultipleMockBean> upload(@FormDataParam("file") InputStream fileInputStream) throws JsonParseException, JsonMappingException, IOException {
		return fileService.loadFile(fileInputStream);
	}
	
	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public StreamingOutput download(@Context HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=backup.json");
		return new StreamingOutput() {
			
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(output, mockService.getAll());
			}
		};
	}
}
