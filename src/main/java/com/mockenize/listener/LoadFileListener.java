package com.mockenize.listener;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mockenize.service.FileService;

@Component
public class LoadFileListener implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${default.file}")
	private File file;

	@Autowired
	private FileService loadFileService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		loadFileService.loadFile(file);
	}

}
