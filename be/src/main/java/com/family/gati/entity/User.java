package com.family.gati.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor // 기본 생성자 세팅
@Table(name = "USER")
public class User {

    @Id // DB 테이블의 PK와 객체의 필드 매핑
    @Column(name = "USER_SEQ")
    @GeneratedValue // 기본 키 자동 생성
    private Long user_seq;

    @Column(name = "USER_ID", length = 20, unique = true)
    @NotNull
    private String userId;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    private String email;

    @Column(name = "PASSWORD", columnDefinition = "CHAR(64)", length = 64, nullable = false)
    @NotNull
    private String password;

    @Column(name = "NICKNAME", length = 20)
    @NotNull
    private String nickName;

    @Column(name = "BIRTH", columnDefinition = "CHAR(8)", length = 8)
    @NotNull
    private String birth;

    @Column(name = "PHONE_NUMBER", columnDefinition = "CHAR(11)", length = 11)
    @NotNull
    private String phoneNumber;

    @Column(name = "MAIN_GROUP")
    private int mainGroup;

    @Column(name = "PLUS_MINUS", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int plusMinus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL ON CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(name = "ACCESS_TOKEN", length = 200)
    @NotNull
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 200)
    @NotNull
    private String refreshToken;

    @Column(name = "SALT", length = 64)
    @NotNull
    private String salt;

    @Column(name = "ROLE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Builder
    public User(String userId, String password, Role role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}
