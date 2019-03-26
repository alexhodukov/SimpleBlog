package com.simpleblog.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.simpleblog.entity.UserEntity;
import com.simpleblog.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void createUser(UserEntity user) {
//		userRepository.insertUser(user);
	}
	
	@Transactional
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}
	
	@Transactional
	public UserEntity findByEmail(String email) {
		Optional<UserEntity> user = userRepository.findByEmail(email);
		System.out.println(user.isPresent());
		return user.get();
	}
	
	
}
