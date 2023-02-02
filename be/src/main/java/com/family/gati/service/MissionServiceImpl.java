package com.family.gati.service;

import com.family.gati.dto.MissionDto;
import com.family.gati.entity.Mission;
import com.family.gati.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    @Override
    public List<MissionDto> findByGroupId(Integer groupId) {
        List<Mission> missions = missionRepository.findByGroupId(groupId);
        int size = missions.size();
        List<MissionDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MissionDto mission = new MissionDto.MissionDtoBuilder(missions.get(i)).build();
            result.add(mission);
        }
        return result;
    }

    public void insertMission(MissionDto missionDto) {
        missionRepository.save(new Mission.MissionBuilder(missionDto).build());
    }
}
