package com.simpleblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.entity.UserEntity;
import com.simpleblog.service.UserService;

@RestController
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "login/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserEntity user) {
		userService.createUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "users/", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> listAllUser() {
		List<UserEntity> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserEntity>>(users, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
	}
}
