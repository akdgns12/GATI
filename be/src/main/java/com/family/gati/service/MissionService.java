package com.family.gati.service;

import com.family.gati.dto.MissionDto;

import java.util.List;

public interface MissionService {
    List<MissionDto> findByGroupId(Integer groupId);
    MissionDto insertMission(MissionDto missionDto);
    MissionDto updateMission(MissionDto missionDto);
    void deleteMissionById(Integer id);
}
