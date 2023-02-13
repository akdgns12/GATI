package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.entity.Family;
import com.family.gati.entity.FamilyMember;
import com.family.gati.entity.User;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.UserRepository;
import com.family.gati.security.jwt.JwtAuthenticationFilter;
import com.family.gati.security.jwt.JwtTokenProvider;
import com.family.gati.service.FamilyMemberService;
import com.family.gati.service.FamilyService;
import com.family.gati.service.NotiService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    private final UserService userService;
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final NotiService notiService;

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
            Family newFamily = null;
            if(user.getMainFamily() == null){
                log.debug("회원");
                newFamily = familyService.getByMasterId(userId);
                user.setMainFamily(newFamily.getId());
                userRepository.save(user);
            }
            resultMap.put("created Family", newFamily);
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
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFamily(@RequestBody DeleteFamilyDto deleteFamilyDto){
        logger.debug("id: {}", deleteFamilyDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.deleteFamily(deleteFamilyDto.getFamilyId());
            familyMemberService.deleteFamilyMember(deleteFamilyDto.getFamilyId());
            // 지울 그룹이 user의 mainFamily이면 user 정보에서도 삭제
            User user = userService.getUserByUserId(deleteFamilyDto.getUserId());
            if(user.getMainFamily() == deleteFamilyDto.getFamilyId()){
                user.setMainFamily(null);
                userRepository.save(user);
            }

            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;

            // 삭제 후 유저 그룹 조회해서 하나도 없다면 생성 화면으로, 하나라도 있다면 main_group 선택 화면으로
            if(familyMemberService.getFamilyListByUserId(user.getUserId()) == null){
                resultMap.put("msg", "유저 그룹 존재 X 생성화면으로");
            }else{
                resultMap.put("msg", "그룹 하나 이상 존재 main_group 선택화면으로");
            }
        }catch (Exception e){
            logger.debug("그룹 삭제 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "그룹 초대", notes = "Dto에 그룹 id, 그룹 명, 초대 받는 사람, type 전달받음")
    @PostMapping
    public ResponseEntity<?> inviteFamily(@RequestBody FamilyInviteDto familyInviteDto){
        logger.debug("familyInviteDto", familyInviteDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        // 이미 가족에 포함된 멤버라면 return
        if(familyMemberService.isAlreadyExist(familyInviteDto.getFamilyId(), familyInviteDto.getReceiverId())){
            logger.debug("이미 존재하는 멤버");
            resultMap.put("msg", FAIL);
            resultMap.put("result", "이미 존재하는 멤버");
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }


        FamilyNotiDto familyNotiDto = new FamilyNotiDto();
        familyNotiDto.setSenderNickname(familyInviteDto.getNickName());
        familyNotiDto.setReceiverId(familyInviteDto.getReceiverId());
        familyNotiDto.setGroupName(familyInviteDto.getGroupName());
        familyNotiDto.setGroupId(familyInviteDto.getFamilyId());
        familyNotiDto.setType(1);

        try{
            notiService.saveFamilyInvite(familyNotiDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            log.debug("그룹 초대 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    // 그룹 초대 수락
    @ApiOperation(value = "그룹 초대 수락", notes = "Family id, userId 받음")
    @PostMapping("/inviteAccpet")
    public ResponseEntity<?> acceptInviteFamily(@RequestBody FamilyInviteDto familyInviteDto){
        logger.debug("userId: {}", familyInviteDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            familyService.acceptInvite(familyInviteDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e) {
            logger.debug("그룹 초대 수락 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 그룹 초대 거절
    @ApiOperation(value = "그룹 초대 거절", notes = "초대 거절시 NOTIFICATION에 등록된 알림 삭제")
    @DeleteMapping("/inviteReject")
    public ResponseEntity<?> rejectInviteFamily(@RequestBody FamilyInviteRejectDto familyInviteRejectDto){
        logger.debug("userId: {}", familyInviteRejectDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            notiService.deleteNoti(familyInviteRejectDto.getUserId(), familyInviteRejectDto.getFamilyId());
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e) {
            logger.debug("초대 거절, 알림 삭제 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 같은 그룹 멤버 조회
    @ApiOperation(value = "같은 그룹 멤버 조회", notes = "familyId 받음")
    @GetMapping("/memberList/{familyId}")
    public ResponseEntity<?> getFamilyMemberInfo(@PathVariable int familyId){
        logger.debug("familyId: {}", familyId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;


        List<FamilyMemberResponseDto> familyMemberInfoList = new ArrayList<>();
        try{
            List<FamilyMember> familyMemberList = familyMemberService.getFamilyMemberListByFamilyId(familyId);
            for(FamilyMember data : familyMemberList) {
                User user = userService.getUserByUserId(data.getUserId());
                FamilyMemberResponseDto familyMemberResponseDto = new FamilyMemberResponseDto();
                familyMemberResponseDto.setUserId(user.getUserId());
                familyMemberResponseDto.setBirth(user.getBirth());
                familyMemberResponseDto.setNickName(user.getNickName());
                familyMemberResponseDto.setPhone(user.getPhoneNumber());
                familyMemberInfoList.add(familyMemberResponseDto);
            }
            resultMap.put("msg", SUCCESS);
            resultMap.put("Info result", familyMemberInfoList);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("그룹 멤버 죄회 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
