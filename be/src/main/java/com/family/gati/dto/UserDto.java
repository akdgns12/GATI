package com.family.gati.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@ApiModel(value = "UserDto", description = "User info")
public class UserDto {

    private String Id;
    private String email;
    private char password;
    private String nickName;
    private char birth;
    private char phoneNumber;
    private int mainGroup;
    private TinyIntTypeDescriptor plusMinus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String accessToken;
    private String refreshToken;
    private String salt;
}
