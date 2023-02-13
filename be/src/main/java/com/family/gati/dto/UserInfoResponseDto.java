package com.family.gati.dto;

import com.family.gati.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class UserInfoResponseDto {
    private int userSeq;
    private String userId;
    private String email;
    private String nickName;
    private String birth;
    private String phoneNumber;
    private Integer mainFamily;
    private int plusMinus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Role role;
}
