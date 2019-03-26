package com.simpleblog.dto;

import javax.validation.constraints.NotBlank;

import com.simpleblog.entity.ArticleStatus;

public class ArticleRequestDto {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String text;
	
	@NotBlank
	private ArticleStatus status;

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
}
