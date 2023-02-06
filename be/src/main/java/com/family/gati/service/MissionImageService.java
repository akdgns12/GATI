package com.family.gati.service;

import com.family.gati.dto.MissionImageDto;

import java.util.List;

public interface MissionImageService {
    List<MissionImageDto> findByGroupId(Integer groupId);
    MissionImageDto insertMissionImage(MissionImageDto missionImageDto);
    MissionImageDto updateMissionImage(MissionImageDto missionImageDto);
    void deleteMissionImageById(Integer id);
}
