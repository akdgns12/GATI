package com.family.gati.security.jwt;

import com.family.gati.repository.UserRepository;
import com.family.gati.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String SECRET_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60;		// 1시간
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7;	// 1주
    private final String AUTHORITIES_KEY = "role";

    @Autowired
    private UserRepository userRepository;

    public JwtTokenProvider(@Value("${app.auth.token.secret-key}")String secretKey) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Key getSignKey(String SECRET_KEY){
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        String userSeq = user.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256) // signWith depecreated
                .setSubject(userSeq)
                .claim(AUTHORITIES_KEY, role)
                .setIssuer("debrains")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public String createAccessTokenByUserInfo(String userId, int userSeq) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위 (sub)
        claims.put("user_seq", userSeq); // 정보는 key / value 쌍으로 저장된다.
        claims.put("userId", userId);  // sub에서 이미 저장했지만, 일단 추가
//        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
//                .claim(AUTHORITIES_KEY, role)
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH)) // set Expire Time
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    private void saveRefreshToken(Authentication authentication, String refreshToken) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        int userSeq = Integer.valueOf(user.getName());

        userRepository.updateRefreshToken(userSeq, refreshToken);
    }

    // Access Token을 검사하고 얻은 정보로 Authentication 객체 생성
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        System.out.println(claims);
        System.out.println(claims.get(AUTHORITIES_KEY));
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        System.out.println(authorities.toString());
        CustomUserDetails principal = new CustomUserDetails(Integer.valueOf(claims.getSubject()), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 복호화해서 유저 정보 얻기
    public int getUserSeq(String token) {
        // depecreated 해결해야 함수 사용가능
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(token);
        String user_seq = String.valueOf(claims.getBody().get("user_seq"));
        return Integer.parseInt(user_seq);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalStateException e) {
            log.info("JWT 토큰이 잘못되었습니다");
        }
        return false;
    }

    // Access Token 만료시 갱신때 사용할 정보를 얻기 위해 Claim 리턴
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
