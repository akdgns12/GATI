package com.family.gati.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter @Setter
public class LoginRequest { // oauth 회원관리 용도 -> 일반 로그인 용도로 변경(oauth 임시 생략)
    String userId;
    String password;
}
