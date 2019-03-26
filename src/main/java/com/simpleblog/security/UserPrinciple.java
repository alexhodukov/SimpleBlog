package com.simpleblog.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simpleblog.entity.UserEntity;

public class UserPrinciple implements UserDetails {
	 
	 private Integer id;
	 
	 private String firstName;
	 
	 private String lastName;
	 
	 private String email;
	 
	 @JsonIgnore
	 private String password;
	 
	 private Collection<? extends GrantedAuthority> authorities;
	 
	 public UserPrinciple(Integer id, String name, 
			 String username, String email, String password, 
			 Collection<? extends GrantedAuthority> authorities) {
		 this.id = id;
	     this.firstName = name;
	     this.lastName = username;
	     this.email = email;
	     this.password = password;
	     this.authorities = authorities;
	 }
	 
	 public static UserPrinciple build(UserEntity user) {
		 List<GrantedAuthority> authorities = Arrays.asList(
				 new SimpleGrantedAuthority(user.getRole().name()));
		 
		 return new UserPrinciple(user.getId(), user.getFirstName(), user.getEmail(), user.getEmail(),
				 user.getPassword(), authorities);
	 }
	 
	 public Integer getId() {
	     return id;
	 }
	 
	 public String getFirstName() {
	     return firstName;
	 }
	 
	 public String getEmail() {
	     return email;
	 }
	 
	 @Override
	 public String getUsername() {
	     return lastName;
	 }
	 
	 @Override
	 public String getPassword() {
	     return password;
	 }
	 
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	     return authorities;
	 }
	 
	 @Override
	 public boolean isAccountNonExpired() {
	     return true;
	 }
	 
	 @Override
	 public boolean isAccountNonLocked() {
	     return true;
	 }
	 
	 @Override
	 public boolean isCredentialsNonExpired() {
	     return true;
	 }
	 
	 @Override
	 public boolean isEnabled() {
	     return true;
	 }
	 
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	        
	     UserPrinciple user = (UserPrinciple) o;
	     return Objects.equals(id, user.id);
	 }

	@Override
	public String toString() {
		return "UserPrinciple [id=" + id + ", name=" + firstName + ", username=" + lastName + ", email=" + email
				+ ", password=" + password + ", authorities=" + authorities + "]";
	}	

}
