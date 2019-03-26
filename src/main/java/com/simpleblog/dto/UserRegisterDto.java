package com.simpleblog.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String f;
 
    @NotBlank
    @Size(min = 3, max = 50)
    private String l;
 
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
 
    public String getName() {
        return f;
    }
 
    public void setName(String name) {
        this.f = name;
    }
 
    public String getUsername() {
        return l;
    }
 
    public void setUsername(String username) {
        this.l = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
