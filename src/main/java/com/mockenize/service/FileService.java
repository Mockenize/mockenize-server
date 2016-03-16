package com.mockenize.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockenize.model.MockBeanList;

@Service
public class FileService {
	
	@Autowired
	private MockenizeService mockenizeService;

	private Log log = LogFactory.getLog(getClass());

	public void loadFile(File file) {
		if (file != null && file.exists()) {
			if (file.isFile() && file.canRead()) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					MockBeanList beanList = mapper.readValue(file, MockBeanList.class);
					mockenizeService.insert(beanList);
				} catch (IOException e) {
					log.error(e);
				}
			} else {
				for (File f : file.listFiles()) {
					loadFile(f);
				}
			}
		}
	}
	
	public void loadFile(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MockBeanList[] list = mapper.readValue(inputStream, MockBeanList[].class);
		for (MockBeanList beanList : list) {
			mockenizeService.insert(beanList);
		}
	}
}
