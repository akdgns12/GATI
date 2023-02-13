package com.family.gati.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class FamilyMemberResponseDto {
    private String userId;
    private String nickName;
    private String birth;
    private String phone;
}
