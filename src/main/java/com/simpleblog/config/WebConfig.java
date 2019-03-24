package com.simpleblog.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.simpleblog.controller" })
public class WebConfig implements WebMvcConfigurer {
	
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	    MappingJackson2HttpMessageConverter messageConverter = new  MappingJackson2HttpMessageConverter();

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new Hibernate5Module());

	    messageConverter.setObjectMapper(mapper);
	    return messageConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    converters.add(jacksonMessageConverter());
	}
}
