package com.family.gati.api;

import com.family.gati.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "refreshToken 검사 API")
@RequestMapping("/refresh")
@RequiredArgsConstructor
public class RefreshApiController {

    private static final Logger logger = LoggerFactory.getLogger(PlanApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "refreshToken 전달받음")
    @PostMapping("/{token}")
    public ResponseEntity<?> checkRefreshToken(String token){
        logger.debug("Refresh Token: {}", token);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // refreshToken이 들어온다면
        if(token.contains("refresh")){
            if (jwtTokenProvider.validateRefreshToken(token)) { // refreshToken 유효성 검사 통과하면, accessToken 재발급
                String userId = jwtTokenProvider.getUserId(token);
                int userSeq = jwtTokenProvider.getUserSeq(token);
                jwtTokenProvider.sendAccessToken(jwtTokenProvider.createAccessTokenByUserInfo(userId, userSeq));
            }else{  // // 만료된 refreshToken 이거나 유저 DB에 저장된 refreshToken과 정보가 다른 경우, login 화면으로 이동하게끔
                resultMap.put("msg", "유효하지 않은 refreshToken 다시 로그인 하세요");
                status = HttpStatus.UNAUTHORIZED;
            }
        }
        }catch (Exception e){

        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
