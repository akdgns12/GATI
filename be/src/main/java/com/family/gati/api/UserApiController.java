package com.family.gati.api;

import com.family.gati.dto.UserDto;
import com.family.gati.entity.AuthProvider;
import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import com.family.gati.payload.SignUpRequest;
import com.family.gati.repository.UserRepository;
import com.family.gati.service.UserService;
import com.family.gati.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "User API")
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    // 회원가입
    @ApiOperation(value = "유저 회원가입", notes = "id, email, password, nickname, birth, phoneNumber")
    @PostMapping("/join")
    public ResponseEntity<?> join(@ApiParam(value = "id, email, password, nickname, birth, phoneNumber")
                                      @RequestBody SignUpRequest request){ // User 엔티티말고, payload.SignUpRequest로 가져오는 형식 고려해보자
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
        newUser.setRole(Role.USER);
        newUser.setCreateTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setAuthProvider(AuthProvider.LOCAL);
        System.out.println(newUser);

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
    public ResponseEntity<?> login(@ApiParam(value = "userId, password 받음") @RequestBody Map<String, String> userInfo){ // 여기도 마찬가지로 payload.LoginRequest로
        User user = userRepository.findByUserId(userInfo.get("userId"));
        logger.info("userId:{} ", user);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            if(user == null){
                resultMap.put("msg", FAIL);
                status = HttpStatus.NO_CONTENT;
                new ResponseEntity<Map<String, Object>>(resultMap, status);
            }

            if(!passwordEncoder.matches(userInfo.get("password"), user.getPassword())){
                logger.debug("비밀번호 불일치: {}", user.getPassword());
                resultMap.put("msg", FAIL);
                status = HttpStatus.NOT_FOUND;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }
            
            String userId = user.getUserId();
            int userSeq = user.getUserSeq();

            // accessToken 발급
            String accessToken = jwtTokenProvider.createAccessTokenByUserInfo(userId, userSeq);
//            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
//            // refreshToken 저장과 함께 DB에 저장됨
//            HttpServletResponse response = null;
//            jwtTokenProvider.createRefreshToken(authentication, response);
//            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
//            System.out.println(authentication.toString());

            resultMap.put("msg", SUCCESS);
            resultMap.put("accessToken", accessToken);
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

        try{
           User userInfo = userService.getUserByUserId(userId);
            logger.debug("userInfo: {}", userInfo);
            if(userInfo == null){
                status = HttpStatus.NO_CONTENT;
            }else{
                resultMap.put("msg", SUCCESS);
                resultMap.put("userInfo", userInfo);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.error("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "메인 그룹 선택", notes = "userId, groupId 전달받음")
    @PutMapping("/main")
    public ResponseEntity<?> modifyMainGroup(@RequestPart(value = "userId") String userId,
                                             @RequestPart(value = "groupId") int groupId){
        logger.debug("userId: {} groupId: {}", userId + " " + groupId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            userService.modifyMainGroup(userId, groupId);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("메인 그룹 선택 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원정보 변경
    @ApiOperation(value = "회원정보 변경")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SignUpRequest request){
        logger.debug("user: {}", request);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        User newUser = new User();
        newUser.setUserId(request.getUserId());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setNickName(request.getNickName());
        newUser.setBirth(request.getBirth());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setRole(Role.USER);
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setAuthProvider(AuthProvider.LOCAL);
        System.out.println(newUser);

        try{
            // 본인 확인 추가해야함

            userService.updateUser(newUser);
            String userId = new UserDto().getUserId(); // 이게 맞아? 흠..
            User modifiedUser = userRepository.findByUserId(userId);
            resultMap.put("msg", SUCCESS);
            resultMap.put("modifiedUser", modifiedUser);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.error("회원정보 변경 실패: {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원 탈퇴
    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable String token){
        logger.debug("userId: {}", token);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            if(!jwtTokenProvider.validateToken(token)){
                resultMap.put("msg", "유효하지 않은 토큰");
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }
            // JwtTokenProvider에 작성 필요
            int userSeq = jwtTokenProvider.getUserSeq(token);  
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

    /*
        소셜
     */
}
