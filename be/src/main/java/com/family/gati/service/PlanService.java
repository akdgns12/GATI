package com.family.gati.service;

import com.family.gati.dto.PlanDto;
import com.family.gati.entity.User;

import java.util.List;

public interface PlanService {

    List<PlanDto> findByGroupId(User user);
    User findByUserId(String userId);
}
