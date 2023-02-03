package com.family.gati.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum Role {
    USER("USER", "일반 사용자 권한"),
    ADMIN("ADMIN", "관리자 권한");

    private final String code;
    private final String displayName;

    public static Role of(String code){
        return Arrays.stream(Role.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(USER);
    }
}
