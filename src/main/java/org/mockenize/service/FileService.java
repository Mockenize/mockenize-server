package org.mockenize.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockenize.model.MultipleMockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

@Service
public class FileService {
	
	@Autowired
	private MockService mockService;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();

	private Log log = LogFactory.getLog(getClass());

	public void loadFile(File file) {
		if (file != null && file.exists()) {
			if (file.isFile() && file.canRead()) {
				try (FileInputStream fileInputStream = new FileInputStream(file)) {
					String json = CharStreams.toString(new InputStreamReader(fileInputStream));
					read(json);
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
	
	public Collection<MultipleMockBean> loadFile(InputStream fileInputStream) throws IOException {
		String json = CharStreams.toString(new InputStreamReader(fileInputStream));
		return read(json);
	}
	
	private Collection<MultipleMockBean> read(String json) throws IOException {
		if(json.startsWith("[")) {
			Collection<MultipleMockBean> multipleMockBeans = mapper.readValue(json, new TypeReference<List<MultipleMockBean>>(){});
			for (MultipleMockBean mockBean : multipleMockBeans) {
				mockService.save(mockBean);
			}
			return multipleMockBeans;
		} else {
			MultipleMockBean mockBean = mapper.readValue(json, MultipleMockBean.class);
			Arrays.asList(mockService.save(mockBean));
		}
		return Arrays.asList();
	}
}
