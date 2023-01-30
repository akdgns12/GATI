package com.family.gati.api;

import com.family.gati.dto.UserDto;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import com.family.gati.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    private final UserService userService;

    // 회원가입
    @ApiOperation(value = "유저 회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user){
        logger.debug("user: {}", user.toString());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // 로그인 처리 & token 작업시 암호화 예정
            userService.join(user);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.CREATED;
        }catch (Exception e){
            logger.debug("회원가입 실패: {}", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    
    // userSeq로 유저 정보 가져오기
    @ApiOperation(value = "유저 정보 리턴")
    @GetMapping("/{userSeq}")
    public ResponseEntity<?> getUserByUserSeq(@ApiParam(value = "path로 userSeq 전달받음") @PathVariable("userSeq") Long userSeq){
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
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@ApiParam(value = "path로 userId 전달받음")@PathVariable("userId") String userId){
        logger.debug("userId: {}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            Optional<User> userInfo = userService.getUserByUserId(userId);
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
    public ResponseEntity<?> modifyMainGroup(@RequestBody Map<String, Object> map){
        String userId = (String)map.get("userId");
        String groupId =(String) map.get("groupId");
        logger.debug("userId: {} groupId: {}", userId + " " + groupId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            userService.modifyMainGroup(map); // test 후 수정 필요
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
    public ResponseEntity<?> modify(@RequestBody User user){
        logger.debug("user: {}", user);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // 본인 확인 추가해야함
            userService.modify(user);
            String userId = new UserDto().getUserId(); // 이게 맞아? 흠..
            Optional<User> modifiedUser = userRepository.findByUserId(userId);
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

    // 회원삭제
    @ApiOperation(value = "회원탈퇴", notes = "넘어온 id와 일치하는 회원 삭제")
    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long userSeq){ // 추후 Token으로 교체해야함
        logger.debug("userId: {}", userSeq);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            userService.deleteUser(userSeq);
        }catch (Exception e){
            logger.debug("회원 삭제 실패: {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
