package com.simpleblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simpleblog.entity.ArticleEntity;
import com.simpleblog.entity.ArticleStatus;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer>{
	List<ArticleEntity> findArticleByStatus(ArticleStatus status);
}
