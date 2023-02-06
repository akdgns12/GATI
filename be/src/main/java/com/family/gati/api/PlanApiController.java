package com.family.gati.api;

import com.family.gati.dto.PlanSignUpDto;
import com.family.gati.entity.Plan;
import com.family.gati.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
@Api(tags = "Plan API")
public class PlanApiController {

    private static final Logger logger = LoggerFactory.getLogger(PlanApiController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final PlanService planService;

    @ApiOperation(value = "일정 생성")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PlanSignUpDto planSignUpDto){ // 일단 userId, groupId도 같이 보내주는걸로?
        logger.debug("planSignUpDto: {}", planSignUpDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            planService.createPlan(planSignUpDto);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.CREATED;
        }catch (Exception e){
            log.debug("일정 생성 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @ApiOperation(value = "일정 정보 조회")
    @GetMapping("/{groupId}")
    public ResponseEntity<?> getPlanInfo(@PathVariable int groupId){
        logger.debug("groupId: {}", groupId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        Plan plan = new Plan();
        try{
            plan = planService.getPlanByGroupId(groupId);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}