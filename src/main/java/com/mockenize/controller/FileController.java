package com.mockenize.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockenize.model.ReturnBean;
import com.mockenize.service.FileService;
import com.mockenize.service.MockenizeService;

@Path("/_mockenize/file")
@Controller
public class FileController {

	@Autowired
	private ResourceBundle bundle;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private MockenizeService mockenizeService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/upload")
	public ReturnBean upload(@FormDataParam("file") InputStream fileInputStream) throws JsonParseException, JsonMappingException, IOException {
		fileService.loadFile(fileInputStream);
		return new ReturnBean(bundle.getString("file.upload"));
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
				mapper.writeValue(output, mockenizeService.getAllMockBeans().values());
			}
		};
	}
}
