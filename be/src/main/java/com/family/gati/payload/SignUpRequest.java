package com.family.gati.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Getter @Setter
public class SignUpRequest {
    String email;
    String userId;
    String password;
    String nickName;
    String birth;
    String phoneNumber;
}
