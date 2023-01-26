package com.family.gati.api;

import com.family.gati.entity.Group;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // userId로 그룹 목록 조회
    @ApiOperation(value = "그룹 목록 조회")
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getGroupByUser(@ApiParam(value = "path로 userId 전달받음") @PathVariable String userId) {
        logger.debug("userId: {}", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            List<Group> groupList = groupService.getGroupByUserId(userId);
            if(groupList == null || groupList.size() < 1) status = HttpStatus.NO_CONTENT;
            else{
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
}
