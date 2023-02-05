package com.family.gati.api;

import com.family.gati.dto.FamilySignUpDto;
import com.family.gati.dto.FamilyUpdateDto;
import com.family.gati.entity.Family;
import com.family.gati.entity.FamilyMember;
import com.family.gati.service.FamilyMemberService;
import com.family.gati.service.FamilyService;
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
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
@Api(tags = "Family API")
public class FamilyApiController {

    private static final Logger logger = LoggerFactory.getLogger(FamilyApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final FamilyService familyService;
    private final FamilyMemberService familyMemberService;

    // 새로운 그룹 생성
    @ApiOperation(value = "그룹 생성", notes = "그룹 생성은 초기 멤버 0")
    @PostMapping
    public ResponseEntity<?> Family(@RequestBody FamilySignUpDto familySignUpDto){
        logger.debug("familySignUpDto: {}", familySignUpDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.createFamily(familySignUpDto);
            resultMap.put("created Family", familySignUpDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.CREATED;
        }catch (Exception e){
            logger.debug("그룹 생성 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 그룹 목록 조회
    @ApiOperation(value = "그룹 목록 조회")
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getFamilyByUserId(@ApiParam(value = "path로 userId 전달받음") @PathVariable String userId) {
        logger.debug("userId: {}", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            List<FamilyMember> familyList = familyMemberService.getFamilyListByUserId(userId);

            if(familyList == null || familyList.size() < 1) status = HttpStatus.NO_CONTENT;
            else{
                // 그룹명을 리턴 시켜줄거면?
                resultMap.put("familyList", familyList);
                resultMap.put("msg", SUCCESS);
                status = HttpStatus.OK;
            }
        }catch (Exception e){
            logger.debug("그룹 목록 조회 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // Main 그룹 조회
    @ApiOperation(value = "Main 그룹 조회")
    @GetMapping("/main/{userId}")
    public ResponseEntity<?> getMainFamilyByUser(@ApiParam(value = "path로 userId 전달받음")
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

    // 그룹 정보 수정
    @ApiOperation(value = "그룹 정보 수정", notes = "그룹 id, 바꿀 그룹 정보(img, name)")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody FamilyUpdateDto familyUpdateDto){
        logger.debug("family: {}", familyUpdateDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.updateFamily(familyUpdateDto);
            resultMap.put("modifedFamily: {}", familyUpdateDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("그룹 정보 수정 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 그룹 삭제
    @ApiOperation(value = "그룹 삭제")
    @DeleteMapping("/familyId")
    public ResponseEntity<?> deleteFamily(@PathVariable String familyId){
        logger.debug("familyId: {}", familyId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.deleteFamily(familyId);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("그룹 삭제 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
