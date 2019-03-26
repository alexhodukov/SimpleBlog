package com.simpleblog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDto {
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;
 
    @NotBlank
    @Size(min = 5, max = 30)
    private String password;
 
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
}
