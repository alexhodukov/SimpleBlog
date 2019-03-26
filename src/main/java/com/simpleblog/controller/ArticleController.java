package com.simpleblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.dto.ArticleRequestDto;
import com.simpleblog.dto.ArticleResponseDto;
import com.simpleblog.entity.ArticleEntity;
import com.simpleblog.service.ArticleService;

@RestController
public class ArticleController {

	private ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("articles")
	public ResponseEntity<Void> createArticle(@Valid @RequestBody ArticleRequestDto article) {
		articleService.create(article);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("articles")
	public ResponseEntity<List<ArticleResponseDto>> listAllArticles() {
		List<ArticleResponseDto> articles = articleService.findAll();
		if (articles.isEmpty()) {
			return new ResponseEntity<List<ArticleResponseDto>>(articles, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<List<ArticleResponseDto>>(articles, HttpStatus.OK);
	}
	
	@GetMapping("articles/{id}")
	public ResponseEntity<ArticleEntity> getArticle(@PathVariable("id") Integer id) {
		ArticleEntity article = articleService.findById(id);
		if (article == null) {
			return new ResponseEntity<ArticleEntity>(HttpStatus.NO_CONTENT);	
		}
		return new ResponseEntity<ArticleEntity>(article, HttpStatus.OK);
	}

}
