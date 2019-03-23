package com.simpleblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simpleblog.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
