package com.family.gati.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@NoArgsConstructor // 기본 생성자 세팅
public class User {

    @Id // DB 테이블의 PK와 객체의 필드 매핑
    @Column(name = "USER_SEQ")
    @GeneratedValue // 기본 키 자동 생성
    private int userSeq;

    @Column(name = "USER_ID", length = 20, nullable = false, unique = true)
    private String userId;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @Column(name = "PASSWORD", columnDefinition = "CHAR(64)", length = 64, nullable = false)
    private String password;

    @Column(name = "NICKNAME", length = 20, nullable = false)
    private String nickName;

    @Column(name = "BIRTH", columnDefinition = "CHAR(8)", length = 8, nullable = false)
    private String birth;

    @Column(name = "PHONE_NUMBER", columnDefinition = "CHAR(11)", length = 11, nullable = false)
    private char phoneNumber;

    @Column(name = "MAIN_GROUP")
    private int mainGroup;

    @Column(name = "PLUS_MINUS", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int plusMinus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL ON CURRENT_TIMESTAMP")
    private Date updateTime;

    @Column(name = "ACCESS_TOKEN", length = 200, nullable = false)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 200, nullable = false)
    private String refreshToken;

    @Column(name = "SALT", length = 64, nullable = false)
    private String salt;
}
