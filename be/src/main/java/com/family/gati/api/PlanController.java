package com.family.gati.api;

import com.family.gati.dto.PlanDto;
import com.family.gati.entity.User;
import com.family.gati.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
@Api(tags = "Plan API")
public class PlanController {
    private final PlanService planService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserAndGroupId(@ApiParam(value = "path로 userID 전달받음")@PathVariable("userId") String userId){
        User user = planService.findByUserId(userId);
        List<PlanDto> findDtos = planService.findByGroupId(user);
        return ResponseEntity.ok(findDtos);
    }
}
