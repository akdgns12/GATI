package com.family.gati.service;

import com.family.gati.dto.PlanSignUpDto;
import com.family.gati.entity.Plan;
import com.family.gati.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan getPlanByGroupId(int groupId){
        return planRepository.findByGroupId(groupId);
    }

    public void createPlan(PlanSignUpDto planSignUpDto){
        Plan plan = new Plan();

        plan.setUserId(planSignUpDto.getUserId());
        plan.setGroupId(planSignUpDto.getGroupId());
        plan.setTitle(planSignUpDto.getTitle());
        plan.setStartDate(planSignUpDto.getStartDate());
        plan.setEndDate(planSignUpDto.getEndDate());
        plan.setPlace(planSignUpDto.getPlace());
        plan.setMemo(planSignUpDto.getMemo());
        plan.setCreateTime(LocalDateTime.now());

        planRepository.save(plan);
    }
}
