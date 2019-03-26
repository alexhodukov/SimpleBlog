package com.simpleblog.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.simpleblog.dto.ArticleRequestDto;
import com.simpleblog.dto.ArticleResponseDto;
import com.simpleblog.dto.UserInfoDto;
import com.simpleblog.entity.ArticleEntity;
import com.simpleblog.entity.UserEntity;

public class ConverterDto {
	public static List<ArticleResponseDto> convertToListEntity(List<ArticleEntity> list) {
		return list.stream()
				.map(ConverterDto::convertToArticleResponseDto)
				.collect(Collectors.toList());
	}
	
	public static ArticleResponseDto convertToArticleResponseDto(ArticleEntity en) {
		ArticleResponseDto dto = new ArticleResponseDto();
		dto.setTitle(en.getTitle());
		dto.setText(en.getText());
		dto.setStatus(en.getStatus());
		dto.setUser(convertToUserEntity(en.getUser()));
		
		return dto;
	}
	
	public static UserInfoDto convertToUserEntity(UserEntity en) {
		UserInfoDto dto = new UserInfoDto();
		dto.setFirstName(en.getFirstName());
		dto.setLastName(en.getLastName());
		dto.setEmail(en.getEmail());
		dto.setCreatedAt(en.getCreatedAt());
		
		return dto;
	}

	public static ArticleEntity convertToArticleEntity(ArticleRequestDto dto) {
		ArticleEntity en = new ArticleEntity();
		en.setTitle(dto.getTitle());
		en.setText(dto.getText());
		en.setStatus(dto.getStatus());
		
		return en;
	}
}
