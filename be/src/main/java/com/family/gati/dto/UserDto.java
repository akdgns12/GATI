package com.family.gati.dto;

import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    /**
     * entity가 알 필요 없는것
     * accessToken, salt?(추후 security에서 암호화 함)
     *
     */
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
    private Role role;

    public User toEntity(){
        User user = User.builder()
                .user_seq(userSeq)
                .userId(userId)
                .email(email)
                .password(password)
                .nickName(nickName)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .mainGroup(mainGroup)
                .plusMinus(plusMinus)
                .refreshToken(refreshToken)
                .role(role.USER)
                .build();

        return user;
    }

}
