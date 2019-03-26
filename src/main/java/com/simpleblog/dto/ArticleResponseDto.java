package com.simpleblog.dto;

import com.simpleblog.entity.ArticleStatus;

public class ArticleResponseDto {
	
	private String title;
	
	private String text;
	
	private ArticleStatus status;
	
	private UserInfoDto user;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArticleStatus getStatus() {
		return status;
	}
	public void setStatus(ArticleStatus status) {
		this.status = status;
	}
	public UserInfoDto getUser() {
		return user;
	}
	public void setUser(UserInfoDto user) {
		this.user = user;
	}
	
}
