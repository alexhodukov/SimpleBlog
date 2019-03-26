package com.simpleblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.simpleblog.service.ArticleService;
import com.simpleblog.service.UserService;

@ComponentScan("com.simpleblog")
public class AppConfig {
	@Bean
	public ArticleService articleService() {
		return new ArticleService();
	}
    
    @Bean UserService userService() {
    	return new UserService();
    }
}
