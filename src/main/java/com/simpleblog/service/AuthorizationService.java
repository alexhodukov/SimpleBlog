package com.simpleblog.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simpleblog.dto.UserLoginDto;
import com.simpleblog.dto.UserRegisterDto;
import com.simpleblog.entity.RoleName;
import com.simpleblog.entity.User;
import com.simpleblog.repository.UserRepository;
import com.simpleblog.security.jwt.JwtProvider;

@Service
public class AuthorizationService {

	private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private JwtProvider jwtProvider;

	private PasswordEncoder encoder;

	public AuthorizationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtProvider jwtProvider, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.encoder = encoder;
	}

	@Transactional
	public String authenticateUser(UserLoginDto userLoginDto) {
		Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateJwtToken(authentication);
	}
	
	@Transactional
	public boolean isRegisterUser(UserRegisterDto userRegisterDto) {
        if(userRepository.existsByEmail(userRegisterDto.getEmail())) {
            return false;
        }
 
        User user = new User(userRegisterDto.getName(), userRegisterDto.getUsername(), userRegisterDto.getEmail(), 
        		encoder.encode(userRegisterDto.getPassword()));
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setRole(RoleName.USER);
        userRepository.save(user);
        return true;
    }
}
