package com.family.gati.service;

import com.family.gati.entity.Plan;
import com.family.gati.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan getPlanByGroupId(int groupId){
        return planRepository.findByGroupId(groupId);
    }
}
