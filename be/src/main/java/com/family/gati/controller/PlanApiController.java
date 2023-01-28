package com.family.gati.controller;

import com.family.gati.dto.PlanSaveRequestDto;
import com.family.gati.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plan")
public class PlanApiController {
    private final PlanService planService;

    @PostMapping
    public ResponseEntity savePlan(@RequestBody PlanSaveRequestDto dto){//user 토큰
        PlanResponseDto saveDto = planService.save(dto);
        return ResponseEntity.ok(saveDto);
    }

}
