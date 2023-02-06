package com.family.gati.service;

import com.family.gati.dto.PlanSignUpDto;
import com.family.gati.dto.UpdatePlanDto;
import com.family.gati.entity.Plan;
import com.family.gati.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan getPlanById(int id){
        return planRepository.findById(id);
    }

    // groupId로 일정 목록 모두 조회
    public List<Plan> getAllPlanByGroupId(int groupId){
        if(planRepository.findAllByGroupId(groupId) == null){
            log.debug("해당 그룹의 일정 없음: {}");
            return null;
        }

        return planRepository.findAllByGroupId(groupId);
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

    @Transactional
    public Plan updatePlan(UpdatePlanDto updatePlanDto){
        Plan originPlan = planRepository.findById(updatePlanDto.getId());

        originPlan.setTitle(updatePlanDto.getTitle());
        originPlan.setMemo(updatePlanDto.getMemo());
        originPlan.setStartDate(updatePlanDto.getStartDate());
        originPlan.setEndDate(updatePlanDto.getEndDate());
        originPlan.setPlace(updatePlanDto.getPlace());
        originPlan.setUpdateTime(LocalDateTime.now());

        return planRepository.save(originPlan);
    }

    @Transactional
    public void deletePlan(int id){
        planRepository.deleteById(id);
    }
}
