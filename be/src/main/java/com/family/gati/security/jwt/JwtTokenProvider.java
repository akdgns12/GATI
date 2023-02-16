package com.family.gati.security.jwt;

import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import com.family.gati.security.CustomUserDetails;
import com.family.gati.security.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtTokenProvider {

    private final String SECRET_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 10;		// 1시간
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 1;	// 1주

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    public JwtTokenProvider(@Value("${app.auth.token.secret-key}")String secretKey) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Key getSignKey(String SECRET_KEY){
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public String createAccessToken(Authentication authentication) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);
//
//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//
//        String userSeq = user.getUsername();
//        String role = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        return Jwts.builder()
//                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256) // signWith depecreated
//                .setSubject(userSeq)
//                .claim(AUTHORITIES_KEY, role)
//                .setIssuer("debrains")
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .compact();
//    }

    public String createAccessTokenByUserInfo(String userId, int userSeq) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위 (sub)
        claims.put("user_seq", userSeq); // 정보는 key / value 쌍으로 저장된다.
        claims.put("userId", userId);  // sub에서 이미 저장했지만, 일단 추가

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH)) // set Expire Time
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    public String createRefreshTokenByUserInfo(String userId, int userSeq) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위 (sub)
        claims.put("user_seq", userSeq); // 정보는 key / value 쌍으로 저장된다.
        claims.put("userId", userId);  // sub에서 이미 저장했지만, 일단 추가

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH)) // set Expire Time
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    public void saveRefreshToken(Authentication authentication, String refreshToken) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        int userSeq = Integer.valueOf(user.getUsername());

        userRepository.updateRefreshToken(userSeq, refreshToken);
    }

    // Access Token을 검사하고 얻은 정보로 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUser(token).getEmail());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    // 유저 객체 얻기
    public User getUser(String token){
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(token);
        String user_seq = String.valueOf(claims.getBody().get("user_seq"));
        return userRepository.findByUserSeq(Integer.parseInt(user_seq));
    }

    // userSeq 얻기
    public int getUserSeq(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(token);
        String user_seq = String.valueOf(claims.getBody().get("user_seq"));
        return Integer.parseInt(user_seq);
    }

    // userId 얻기
    public String getUserId(String token){
        User user = getUser(token);
        String userId = user.getUserId();
        return userId;
    }

    // 토큰 유효성, 만료시간 검사
    public Boolean validateToken(String token) {
        try {
            log.debug("유효성 검사 시작");
            Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 accessToken 입니다.");
            log.info("refreshToken을 보내주세요.");
            // 클라이언트는 accessToken가지고만 로그인 요청, accessToken 만료시 refreshToken 쏴달라고 go
//            sendResponse();
            // 클라이언트는 자기가 가지고 있던 refreshToken 보내면 우리는 그 refreshToken이랑 DB에 있는 user의 refreshToken 비교
            sendRequestForRefreshToken();
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 accessToken 입니다.");
        } catch (IllegalStateException e) {
            log.info("accessToken 잘못되었습니다");
        }
        return false;
    }

    public ResponseEntity<?> sendRequestForRefreshToken(){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        resultMap.put("msg", "refreshToken 보내세요");
        status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    public ResponseEntity<?> sendAccessToken(String accessToken){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        log.debug("accessToken 재발급");
        resultMap.put("재발급 accessToken", accessToken);
        resultMap.put("HTTP error Code", "401");
        status = HttpStatus.UNAUTHORIZED;
        
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // RefreshToken 유효성 검사
    public Boolean validateRefreshToken(String refreshToken){
        try{
            int userSeq = getUserSeq(refreshToken);
            String dbRefreshToken = userRepository.getRefreshTokenByUserSeq(userSeq);
            if(!refreshToken.equals(dbRefreshToken)){ // 전달받은 refreshToken과 db에 있는 refreshToken 정보 다르면 유효성 검사 통과 실패
                return false;
            }

            Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(refreshToken);
            return true;
        }catch (ExpiredJwtException e){ // 만료된 refreshToken이라면 DB에 있는 refreshToken 지우고, login화면으로 돌아가게끔
            log.debug("만료된 RefreshToken 입니다.");
            int userSeq = getUserSeq(refreshToken);
            userRepository.updateRefreshToken(userSeq, null);
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 RefreshToken 입니다.");
        } catch (IllegalStateException e){
            log.info("RefreshToken 잘못되었습니다.");
        }
        return false;
    }

    public ResponseEntity<?> sendRefreshToken(String refreshToken){
        log.debug("sendRefreshToken: {}", refreshToken);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        resultMap.put("refreshToken : ", refreshToken);
        status = HttpStatus.CREATED;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // Access Token 만료시 갱신때 사용할 정보를 얻기 위해 Claim 리턴
    private Claims parseClaims(String accessToken) {
        try {
            log.debug("pasreClaims", "Claim 리턴");
            return Jwts.parserBuilder().setSigningKey(getSignKey(SECRET_KEY)).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) { // 만료된 경우
            log.debug("만료된 토큰입니다.", e.getClaims());
            return e.getClaims();
        }
    }
}
