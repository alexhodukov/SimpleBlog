package com.simpleblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.entity.Article;
import com.simpleblog.service.ArticleService;

@RestController
//@RequestMapping("/")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "articles/", method = RequestMethod.GET)
	public ResponseEntity<List<Article>> listAllArticles() {
		List<Article> articles = articleService.findAll();
		if (articles.isEmpty()) {
			return new ResponseEntity<List<Article>>(articles, HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	}

}
