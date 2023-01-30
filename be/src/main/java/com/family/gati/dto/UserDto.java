package com.family.gati.dto;

import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserDto {
    /**
     * entity가 알 필요 없는것
     * accessToken, salt?(추후 security에서 암호화 함)
     */
    private int userSeq;
    private String userId;
    private String email;
    private String password;
    private String nickName;
    private String birth;
    private String phoneNumber;
    private int mainGroup;
    private int plusMinus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String accessToken;
    private String refreshToken;
    private Role role;

    public UserDto(User user){
        this.userSeq = user.getUser_seq();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
        this.birth = user.getBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.mainGroup = user.getMainGroup();
        this.plusMinus = user.getPlusMinus();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        this.accessToken = user.getAccessToken();
        this.refreshToken = user.getRefreshToken();
        this.role = user.getRole();
    }

}
