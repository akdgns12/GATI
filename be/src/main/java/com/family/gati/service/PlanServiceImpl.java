package com.family.gati.service;

import com.family.gati.dto.PlanDto;
import com.family.gati.entity.Plan;
import com.family.gati.entity.User;
import com.family.gati.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;
    private User user = new User();
    @Override
    public List<PlanDto> findByGroupId(User user) {
        Integer groupId = user.getGroupId();
        List<Plan> plans = planRepository.findByGroupId(groupId);
        int size = plans.size();
        List<PlanDto> result = new ArrayList<>();
        for (int i=0; i<size; i++){
            PlanDto plan = new PlanDto.PlanDtoBuilder(plans.get(i)).build();
            result.add(plan);
        }
        return result;
    }
    @Override
    public User findByUserId(String userId){
        user = planRepository.findByUserId(userId);
        return user;
    }
}
