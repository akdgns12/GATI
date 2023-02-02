package com.family.gati.service;

import com.family.gati.dto.MissionDto;

import java.util.List;

public interface MissionService {
    List<MissionDto> findByGroupId(Integer groupId);
    void insertMission(MissionDto missionDto);
}
