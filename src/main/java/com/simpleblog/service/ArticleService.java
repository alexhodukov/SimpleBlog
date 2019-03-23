package com.simpleblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleblog.entity.Article;
import com.simpleblog.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	
	@Transactional(readOnly = true)
	public List<Article> findAll() {
		List<Article> list = new ArrayList<Article>();
		Iterable<Article> it = articleRepository.findAll();
		it.forEach(list::add);
		return list;
	}
	
	@Transactional(readOnly = true)
	public Article findById(Long id) {
		
		return null;
	}
	
	public Article save(Article article) {
		
		return null;
	}
	
}
