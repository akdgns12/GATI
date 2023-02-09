package com.family.gati.service;

import com.family.gati.dto.MissionCompleteDto;
import com.family.gati.dto.MissionDto;
import com.family.gati.dto.MissionRegistDto;

import java.util.List;

public interface MissionService {
    List<MissionDto> findByGroupId(Integer groupId);
    MissionDto insertMission(MissionDto missionDto);
    MissionDto updateMission(MissionDto missionDto);
    void deleteMissionById(Integer id);
    MissionDto findById(Integer id);
    MissionDto findMissionThisWeek(Integer groupId);
    MissionDto setMissionMemNumber(MissionRegistDto missionRegistDto);
    MissionDto completeMission(MissionCompleteDto missionCompleteDto);
}
