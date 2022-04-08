package com.juaracoding.utility;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	//localhost:8080/foto-figure/tanpa nama.png
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		exposeDirectory("foto-peserta", registry);
	}
	
	public void exposeDirectory (String dirName, ResourceHandlerRegistry registry) {
		Path directory = Paths.get(dirName);
		String path = directory.toFile().getAbsolutePath();

		if(dirName.startsWith("../")) {
			dirName=dirName.replace("../", "");
		}
		
		registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+path+"/");
	}

}
