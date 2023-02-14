package com.family.gati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDto {
    private String userId;
    private String email;
    private String nickName;
    private String birth;
    private String phoneNumber;
}
