package com.family.gati.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Getter @Setter
public class UserSignUpRequestDto {
    private String email;
    private String userId;
    private String password;
    private String nickName;
    private int plusMinus;
    private String birth;
    private String phoneNumber;
}
