package com.family.gati.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter @Setter
public class LoginRequest { // oauth 회원관리 용도
    String id;
    String password;
}
