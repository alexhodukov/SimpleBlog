package com.simpleblog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleblog.converter.ConverterDto;
import com.simpleblog.dto.ArticleRequestDto;
import com.simpleblog.dto.ArticleResponseDto;
import com.simpleblog.entity.ArticleEntity;
import com.simpleblog.entity.ArticleStatus;
import com.simpleblog.repository.ArticleRepository;
import com.simpleblog.repository.UserRepository;
import com.simpleblog.security.UserPrinciple;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;
	
	private UserRepository userRepository;

	public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public List<ArticleResponseDto> findAll() {
		List<ArticleEntity> list = articleRepository.findArticleByStatus(ArticleStatus.PUBLIC);
		return ConverterDto.convertToListEntity(list);
	}
	
	@Transactional(readOnly = true)
	public ArticleEntity findById(Integer id) {
		Optional<ArticleEntity> article = articleRepository.findById(id);
		if (!article.isPresent()) {
			return null;
		}
		return article.get();
	}
	
	@Transactional
	public void create(ArticleRequestDto article) {
		UserPrinciple dto = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer id = userRepository.findUserByEmail(dto.getEmail()).get().getId();
		ArticleEntity entity = ConverterDto.convertToArticleEntity(article);
		entity.setId(id);
		entity.setCreatedAt(new Date(System.currentTimeMillis()));
		entity.setUpdatedAt(new Date(System.currentTimeMillis()));
		articleRepository.save(entity);
	}
	
	
	
}
