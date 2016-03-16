package org.mockenize.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockenize.model.MultipleMockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FileService {
	
	@Autowired
	private MockService mockService;

	private Log log = LogFactory.getLog(getClass());

	public void loadFile(File file) {
		if (file != null && file.exists()) {
			if (file.isFile() && file.canRead()) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					MultipleMockBean mockBean = mapper.readValue(file, MultipleMockBean.class);
					mockService.save(mockBean);
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
		MultipleMockBean[] multipleMockBeans = mapper.readValue(inputStream, MultipleMockBean[].class);
		for (MultipleMockBean mockBean : multipleMockBeans) {
			mockService.save(mockBean);
		}
	}
}
