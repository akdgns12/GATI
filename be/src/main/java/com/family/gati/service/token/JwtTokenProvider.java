package com.family.gati.service.token;

import com.family.gati.repository.UserRepository;
import com.family.gati.service.auth.CustomUserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;

    // application 속성을 읽는 어노테이션
    @Value("${jwt.secretKey}")
    private String secretKey;

    private long accessTokenValidTime = 24 * 60 * 60 * 1000L; // 하루
    private long refreshTokenValidTime = 24 * 60 * 60 * 7 * 1000L; // 7일

    private final CustomUserDetailService customUserDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 생성(by email)
    public String createToken(String userId, int userSeq) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload에 저장되는 정보단위
        claims.put("userId", userId); // key / value 쌍으로 저장됨
        claims.put("userSeq", userSeq);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보저장
                .setIssuedAt(now) // 토큰 발행시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // 만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 알고리즘과 sigature에 들어갈 secretKey 값
                .compact();
    }

    // refreshToken 발급
    public String createRefreshToken(String userId, int userSeq){ 
        // 오직 재발행 목적으로만, sub 불필요
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰으로 인증객체(Authentication)을 얻기 위한 메서드
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    // JWT 복호화해서 유저정보 얻기
    public String getUserEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
    
    public int getUserSeq(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        String user_seq = String.valueOf(claims.getBody().get("user_seq"));
        return Integer.parseInt(user_seq);
    }

    // 토큰은 HTTP Header에 저장되어 계속 이용됨(토큰을 사용하기 위해 실제로 Header에서 꺼내오는 메서드)
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }

    // 토큰 유효성 + 만료일자 체크
    public boolean validateTokenExceptExpiration(String token) { 
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException ex){
            log.error("만료된 JWT token");
            throw ex;
        }
        catch(Exception e) {
            return false;
        }
    }

}
