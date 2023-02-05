package com.family.gati.dto;

import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserDto {

    private int userSeq;
    private String userId;
    private String email;
    private String password;
    private String nickName;
    private String birth;
    private String phoneNumber;
    private int mainFamily;
    private int plusMinus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String refreshToken;
    private Role role;

    public UserDto(User user){
        this.userSeq = user.getUserSeq();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
        this.birth = user.getBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.mainFamily = user.getMainGroup();
        this.plusMinus = user.getPlusMinus();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        this.refreshToken = user.getRefreshToken();
        this.role = user.getRole();
    }

    public UserDto(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
