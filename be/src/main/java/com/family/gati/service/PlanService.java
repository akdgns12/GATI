package com.family.gati.service;

import com.family.gati.dto.PlanDto;

import java.util.List;

public interface PlanService {
    List<PlanDto> findByGroupId(int groupId);
}
