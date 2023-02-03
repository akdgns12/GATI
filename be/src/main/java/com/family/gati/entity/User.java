package com.family.gati.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter @Setter
@ToString
@NoArgsConstructor // 기본 생성자 세팅
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @JsonIgnore
    @Id // DB 테이블의 PK와 객체의 필드 매핑
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키 생성 DB에 위임(id값 null이면 AUTO_INCREMENT)
    private int userSeq;

    @Column(name = "USER_ID", length = 20, unique = true)
    @NotNull
    private String userId;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    private String email;

    @Column(name = "PASSWORD", columnDefinition = "CHAR(200)", length = 200, nullable = false)
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
    private Integer mainGroup;

    @Column(name = "PLUS_MINUS", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int plusMinus;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    @NotNull
    private LocalDateTime createTime;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Column(name = "REFRESH_TOKEN", length = 200)
//    @NotNull
    private String refreshToken;

    @Column(name = "ROLE", length = 20)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "AUTH_PROVIDER", length = 20)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String providerId;

    public User(
            String userId,
            String email,
            String password,
            String nickName,
            String birth,
            String phoneNumber,
            int mainGroup,
            int plusMinus,
            LocalDateTime createTime,
            LocalDateTime updateTime,
            String refreshToken,
            Role role,
            AuthProvider authProvider,
            String providerId
    ){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.mainGroup = mainGroup;
        this.plusMinus = plusMinus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.refreshToken = refreshToken;
        this.role = role != null ? role : Role.USER;
        this.authProvider = authProvider != null ? authProvider : authProvider.LOCAL;
        this.providerId = providerId;
    }

////    @Transactional
//    public void encodePassword(PasswordEncoder passwordEncoder){
//        password = passwordEncoder.encode(password);
//    }
}
