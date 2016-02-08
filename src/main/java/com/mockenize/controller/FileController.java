package com.mockenize.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mockenize.model.ReturnBean;
import com.mockenize.service.FileService;

@Path("/_mockenize")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class FileController {

	@Autowired
	private ResourceBundle bundle;

	@Autowired
	private FileService loadFileService;

	@Path("/file")
	@POST
	public ReturnBean upload(@FormDataParam("file") InputStream fileInputStream) throws JsonParseException, JsonMappingException, IOException {
		loadFileService.loadFile(fileInputStream);
		return new ReturnBean(bundle.getString("file.upload"));
	}
}
