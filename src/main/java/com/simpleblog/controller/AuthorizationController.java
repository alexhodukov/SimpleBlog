package com.simpleblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleblog.dto.UserLoginDto;
import com.simpleblog.dto.UserRegisterDto;
import com.simpleblog.message.response.JwtResponse;
import com.simpleblog.service.AuthorizationService;

@RestController
public class AuthorizationController {
	
	private AuthorizationService authorizationService;
	
    @Autowired
	public AuthorizationController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody UserLoginDto loginRequest) {
    	String jwt = authorizationService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto registerRequest) {
    	if (authorizationService.isRegisterUser(registerRequest)) {
    		return new ResponseEntity<String>("You are authorized!", HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>("Email is already in use!", HttpStatus.BAD_REQUEST);
    	}
    }
}
