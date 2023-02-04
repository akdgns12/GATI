package com.family.gati.api;

import com.family.gati.entity.Group;
import com.family.gati.entity.GroupMember;
import com.family.gati.service.GroupMemberService;
import com.family.gati.service.GroupService;
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
@RequestMapping("/group")
@Api(tags = "Group API")
public class GroupApiController {

    private static final Logger logger = LoggerFactory.getLogger(GroupApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    // 새로운 그룹 생성
    @ApiOperation(value = "그룹 생성")
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Group group){
        logger.debug("group: {}", group);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // 그룹명 중복체크(front or back)?
            groupService.createGroup(group);
            resultMap.put("created Group", group);
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
    public ResponseEntity<?> getGroupByUser(@ApiParam(value = "path로 userId 전달받음") @PathVariable String userId) {
        logger.debug("userId: {}", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
//            List<Group> groupList = groupService.getGroupListByUserId(userId);
            List<GroupMember> groupList = groupMemberService.getGroupListByUserId(userId);

            if(groupList == null || groupList.size() < 1) status = HttpStatus.NO_CONTENT;
            else{
                // 그룹명을 리턴 시켜줄거면?
                resultMap.put("group List", groupList);
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
    public ResponseEntity<?> getMainGroupByUser(@ApiParam(value = "path로 userId 전달받음")
                                                @PathVariable String userId){
        logger.debug("userId: {}", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            GroupMember mainGroup = groupMemberService.getMainGroupMemberByUserId(userId);
            if(mainGroup == null) status = HttpStatus.NO_CONTENT;
            else{
                resultMap.put("Main group", mainGroup);
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
    @ApiOperation(value = "그룹 정보 수정")
    @PutMapping
    public ResponseEntity<?> modfiy(@RequestBody @ApiParam(value = "수정할 그룹 객체 전달받음") Group group){
        logger.debug("group: {}", group);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // 1. 요청받은 groupId와 같은 groupId인 group
            int groupId = group.getGroupId();
            Group modifiedGroup = groupService.getGroupByGroupId(groupId);
            groupService.modifyGroup(modifiedGroup);
            resultMap.put("modifedGroup: {}", modifiedGroup);
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
    @DeleteMapping("/groupId")
    public ResponseEntity<?> deleteGroup(@PathVariable String groupId){
        logger.debug("groupId: {}", groupId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            groupService.deleteGroup(groupId);
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
