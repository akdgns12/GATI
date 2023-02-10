package com.family.gati.api;

import com.family.gati.dto.FamilyMemberDto;
import com.family.gati.dto.FamilySignUpDto;
import com.family.gati.dto.FamilyUpdateDto;
import com.family.gati.entity.Family;
import com.family.gati.entity.FamilyMember;
import com.family.gati.entity.User;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.UserRepository;
import com.family.gati.security.jwt.JwtAuthenticationFilter;
import com.family.gati.security.jwt.JwtTokenProvider;
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

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    // 새로운 그룹 생성
    @ApiOperation(value = "그룹 생성", notes = "그룹 생성은 초기 멤버 1(본인)")
    @PostMapping("/{userId}")
    public ResponseEntity<?> Family(@PathVariable("userId") String userId,
                                    @RequestBody FamilySignUpDto familySignUpDto){
        logger.debug("familySignUpDto: {}", familySignUpDto);
        logger.debug("");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.createFamily(userId, familySignUpDto);

            // 첫 그룹 생성 시, 생성 그룹이 mainFamily가 될 수 있게
            User user = userRepository.findByUserId(userId);
            if(user.getMainFamily() == null){
                log.debug("회원");
                Family newFamily = familyService.getByMasterId(userId);
                user.setMainFamily(newFamily.getId());
                userRepository.save(user);
            }
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
            // familyMember의 family id로 family INFO List 작성
            List<Family> familysInfo = new ArrayList<>();
            for(FamilyMember data : familyList){
                int id = data.getFamilyId();

                familysInfo.add(familyService.getFamilyById(id));
            }

            if(familyList == null || familyList.size() < 1 || familysInfo == null || familysInfo.size() < 1) status = HttpStatus.NO_CONTENT;
            else{
                resultMap.put("familyList", familysInfo);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFamily(@PathVariable int id){
        logger.debug("id: {}", id);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.deleteFamily(id);
            familyMemberService.deleteFamilyMember(id);
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
