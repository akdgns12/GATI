package com.family.gati.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class UserDto {

    private Long userSeq;
    private String userId;
    private String email;
    private String password;
    private String nickName;
    private String birth;
    private String phoneNumber;
    private int mainGroup;
    private int plusMinus;
    private Date createTime;
    private Date updateTime;
    private String accessToken;
    private String refreshToken;
    private String salt;
}
