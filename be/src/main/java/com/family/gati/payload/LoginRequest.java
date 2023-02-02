package com.family.gati.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    String email;
    String password;
//    @NotBlank
//    @Email
//    private String email;
//
//    @NotBlank
//    private String password;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Object getEmailForAm(){
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public Object getPasswordForAm(){
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
