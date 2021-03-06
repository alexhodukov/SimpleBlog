package com.simpleblog.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simpleblog.entity.UserEntity;
import com.simpleblog.repository.UserRepository;
import com.simpleblog.security.UserPrinciple;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	UserEntity user = userRepository.findByEmail(email)
    			.orElseThrow(() -> 
    			new UsernameNotFoundException("User Not Found with -> email : " + email));

      return UserPrinciple.build(user);
    }

}