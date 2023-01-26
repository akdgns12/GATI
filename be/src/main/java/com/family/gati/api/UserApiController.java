package com.family.gati.api;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final UserService userService;

    // userSeq로 유저 정보 가져오기
    @GetMapping("/{userSeq}")
    @ApiOperation(value = "유저 정보 리턴")
    public ResponseEntity<?> getUserByUserSeq(@ApiParam(value = "path로 userSeq 전달받음") @PathVariable("userSeq") Long userSeq){
        logger.debug("userSeq: {}", userSeq);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            User userInfo =  userRepository.findByUserSeq(userSeq);
            logger.debug("userInfo: {}", userInfo);
            if(userInfo == null){ // 해당 유저가 없을 때
                status = HttpStatus.NO_CONTENT;
            }else{
                resultMap.put("msg", SUCCESS);
                resultMap.put("userInfo", userInfo);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.error("유저 정보 불러오기 실패: {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // userId로 유저 정보 가져오기
    @GetMapping("/{userId}")
    @ApiOperation("유저 정보 리턴")
    public ResponseEntity<?> getUserByUserId(@ApiParam(value = "path로 userId 전달받음")@PathVariable("userId") String userId){
        logger.debug("userId: {}", userId);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            User userInfo = userRepository.findByUserId(userId);
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
}
