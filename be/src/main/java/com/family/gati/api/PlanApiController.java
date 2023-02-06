package com.family.gati.api;

import com.family.gati.dto.PlanSignUpDto;
import com.family.gati.dto.UpdatePlanDto;
import com.family.gati.entity.Plan;
import com.family.gati.entity.User;
import com.family.gati.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @ApiOperation(value = "일정 등록")
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

        try{
            List<Plan> planList = planService.getAllPlanByGroupId(groupId);
            if(planList.size() < 1) { // 일정 없으면 NO_CONTENT
                resultMap.put("msg", FAIL);
                status = HttpStatus.NO_CONTENT;
                return new ResponseEntity<Map<String, Object>>(resultMap, status);
            }
            resultMap.put("msg", SUCCESS);
            resultMap.put("result", planList);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    @ApiOperation(value = "일정 정보 변경", notes = "path로 Plan id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdatePlanDto updatePlanDto){
        logger.debug("id: {}", updatePlanDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            // 작성자 본인이 맞다면 변경
            // 로그인한 userId != updatePlanDto.getUserId()
//            if(updatePlanDto.getUserId() != userId) {
//                resultMap.put("msg", FAIL);
//                status = HttpStatus.NOT_ACCEPTABLE;
//                return new ResponseEntity<Map<String, Object>>(resultMap, status);
//            }
            Plan modifiedPlan = planService.updatePlan(updatePlanDto);
            resultMap.put("msg", SUCCESS);
            resultMap.put("result", modifiedPlan);
            status = HttpStatus.OK;
        }catch (Exception e){
            log.debug("plan 정보 변경 실패: {}", e.getMessage());
            resultMap.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 일정 삭제
    @ApiOperation(value = "일정삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        Plan plan = planService.getPlanById(id);
        logger.debug("userId: {}", plan);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            planService.deletePlan(id);
            resultMap.put("msg", SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            logger.debug("회원 삭제 실패: {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}