package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.entity.Family;
import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import com.family.gati.security.jwt.JwtAuthenticationFilter;
import com.family.gati.service.FamilyService;
import com.family.gati.service.UserService;
import com.family.gati.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "User API")
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(PlanApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FamilyService familyService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Value("${spring.mail.username}")
    private String from; // 발신자 메일(관리자)

    // 회원가입
    @ApiOperation(value = "유저 회원가입", notes = "id, email, password, nickname, birth, phoneNumber")
    @PostMapping("/join")
    public ResponseEntity<?> join(@ApiParam(value = "id, email, password, nickname, birth, phoneNumber")
                                      @RequestBody UserSignUpRequestDto request){ // User 엔티티말고, payload.SignUpRequest로 가져오는 형식 고려해보자
        logger.debug("user: {}", request.toString());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        User newUser = new User();
        newUser.setUserId(request.getUserId());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setNickName(request.getNickName());
        newUser.setBirth(request.getBirth());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPlusMinus(request.getPlusMinus());
        newUser.setRole(Role.USER);
        newUser.setCreateTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());

        try{
            userService.join(newUser);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.CREATED;
        }catch (Exception e){
            logger.debug("회원가입 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * 로그인 JWT 발급
     * @param {userId, password}
     * @return
     */
    @ApiOperation(value = "일반 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@ApiParam(value = "userId, password 받음") @RequestBody UserLoginDto userLoginDto){
        User user = userRepository.findByUserId(userLoginDto.getUserId());
        logger.info("userId:{} ", user);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            if(user == null){
                resultMap.put("msg", FAIL);
                status = HttpStatus.NO_CONTENT;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
                logger.debug("비밀번호 불일치: {}", user.getPassword());
                resultMap.put("msg", FAIL);
                status = HttpStatus.NO_CONTENT;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            String userId = user.getUserId();
            int userSeq = user.getUserSeq();

            // 유저 로그인할 때 accessToken, refreshToken 둘 다 재발급
            String accessToken = jwtTokenProvider.createAccessTokenByUserInfo(userId, userSeq);
            String refreshToken = jwtTokenProvider.createRefreshTokenByUserInfo(userId, userSeq);
            userRepository.updateRefreshToken(userSeq, refreshToken);

            resultMap.put("msg", SUCCESS);
            // 유저 로그인 성공시 accessToken, refreshToken 모두 보내줌
            resultMap.put("accessToken", accessToken);
            resultMap.put("refreshToken", refreshToken);
            resultMap.put("userId", user.getUserId());
            resultMap.put("role", user.getRole());
            resultMap.put("mainGroup Info", getMainFamilyByUserId(user.getUserId()));
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("일반 로그인 실퍠: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // userSeq로 유저 정보 가져오기
    @ApiOperation(value = "유저 정보 리턴")
    @GetMapping("/seq/{userSeq}")
    public ResponseEntity<?> getUserByUserSeq(@ApiParam(value = "path로 userSeq 전달받음") @PathVariable("userSeq") int userSeq){
        logger.debug("userSeq: {}", userSeq);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            User userInfo =  userService.getUserByUserSeq(userSeq);
            logger.debug("userInfo: {}", userInfo);
            if(userInfo == null){ // 해당 유저가 없을 때
                status = HttpStatus.NO_CONTENT;
            }else{
                resultMap.put("msg", SUCCESS);
                resultMap.put("userInfo", userInfo);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.error("유저 정보 불러오기 실패: {}", e.getMessage());
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // userId로 유저 정보 가져오기
    @ApiOperation("유저 정보 리턴")
    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUserByUserId(@ApiParam(value = "path로 userId 전달받음")@PathVariable("userId") String userId){
        logger.debug("userId: {}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        try{
           User user = userService.getUserByUserId(userId);

           userInfoResponseDto.setUserSeq(user.getUserSeq());
           userInfoResponseDto.setUserId(user.getUserId());
           userInfoResponseDto.setEmail(user.getEmail());
           userInfoResponseDto.setBirth(user.getBirth());
           userInfoResponseDto.setMainFamily(user.getMainFamily());
           userInfoResponseDto.setNickName(user.getNickName());
           userInfoResponseDto.setPhoneNumber(user.getPhoneNumber());
           userInfoResponseDto.setPlusMinus(user.getPlusMinus());
           userInfoResponseDto.setCreateTime(user.getCreateTime());
           userInfoResponseDto.setUpdateTime(user.getUpdateTime());
           userInfoResponseDto.setRole(user.getRole());

            logger.debug("userInfo: {}", userInfoResponseDto);
            if(userInfoResponseDto == null){
                status = HttpStatus.NO_CONTENT;
            }else{
                resultMap.put("msg", SUCCESS);
                resultMap.put("userInfo", userInfoResponseDto);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.error("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "메인 그룹 선택", notes = "userId, familyId 전달받음")
    @PutMapping("/main")
    public ResponseEntity<?> selectMainFamily(@RequestBody UserSelectMainDto selectMainDto){
        logger.debug("userId: {} familyId: {}", selectMainDto.getUserId() + " " + selectMainDto.getMainFamily());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            userService.selectMainFamily(selectMainDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("메인 그룹 선택 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // Main 그룹 조회
    @ApiOperation(value = "Main 그룹 조회")
    @GetMapping("/main/{userId}")
    public ResponseEntity<?> getMainFamilyByUserId(@ApiParam(value = "path로 userId 전달받음")
                                                 @PathVariable String userId){
        logger.debug("userId: {}", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            Family mainFamily = familyService.getMainFamilyByUserId(userId);

            if(mainFamily == null) status = HttpStatus.NO_CONTENT;
            else{
                resultMap.put("Main family", mainFamily);
                resultMap.put("msg", SUCCESS);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.debug("메인 그룹 조회 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원정보 변경
    @ApiOperation(value = "회원정보 변경")
    @PutMapping("/change")
    public ResponseEntity<?> update(@RequestBody UserUpdateDto userUpdateDto){
        logger.debug("user: {}", userUpdateDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            userService.updateUser(userUpdateDto.getUserId(), userUpdateDto);
            User modifiedUser = userRepository.findByUserId(userUpdateDto.getUserId());
            resultMap.put("msg", SUCCESS);
            resultMap.put("modifiedUser", modifiedUser);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.error("회원정보 변경 실패: {}", e);
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원 탈퇴
    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable String userId){
        logger.debug("userId: {}", userId);
        User user = userRepository.findByUserId(userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // JwtTokenProvider에 작성 필요
            int userSeq = user.getUserSeq();
            userService.deleteUser(userSeq);

            // 스프링 시큐리티 - 인증 후 인증 결과(user 객체, 권한 정보)를 담고 SecurityContext에 저장됨
            // 회원탈퇴시 clearContext로 기존 정보 초기화
            SecurityContextHolder.clearContext(); 
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("회원 삭제 실패: {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 비밀번호 찾기
    @ApiOperation(value = "비밀번호 찾기", notes = "입력받은 이메일로 임시 비밀번호 전달")
    @PostMapping("/findPassword/{email}")
    public ResponseEntity<?> findPassword(@PathVariable("email") String email){
        logger.debug("email: {}", email);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            MailDto mail = userService.createMailAndChangePassword(email);
            userService.mailSend(mail, from);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("비밀번호 찾기 이메일 발송 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "아이디 찾기", notes = "입력받은 이메일로 아이디 전달")
    @PostMapping("/findId/{email}")
    public ResponseEntity<?> findId(@PathVariable("email") String email){
        logger.debug("email: {}", email);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            MailDto mail = userService.createMailForId(email);
            userService.mailSend(mail, from);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("아이디 찾기 이메일 발송 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Data
    public static class changePasswordRequest{
        private String userId;
        private String password;
        private String changePassword;
    }

    // 유저 비밀번호 변경
    @ApiOperation(value = "비밀번호 변경")
    @PutMapping("/account/password")
    public ResponseEntity<?> changePassword(@RequestBody changePasswordRequest request){
        logger.debug("token: {}", request);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            User user = userRepository.findByUserId(request.getUserId());
            // 입력받은 비밀번호가 user 비밀번호와 일치할시 변경
            if(passwordEncoder.matches(request.password, user.getPassword())) { 
                userService.changePassword(user, request.getChangePassword());
                resultMap.put("msg", SUCCESS);
                status = HttpStatus.ACCEPTED;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }else{
                resultMap.put("msg", "비밀번호 틀림");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }
        }catch (Exception e){
            log.debug("비밀번호 변경 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃시 refreshToken 삭제")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String token = jwtAuthenticationFilter.parseBearerToken(request);
        User user = jwtTokenProvider.getUser(token);
        logger.debug("user", user);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            user.setRefreshToken(null);
            userRepository.save(user);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("로그아웃 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
