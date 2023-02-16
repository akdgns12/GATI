package com.family.gati.service;

import com.family.gati.dto.MissionCompleteDto;
import com.family.gati.dto.MissionDto;
import com.family.gati.dto.MissionRegistDto;
import com.family.gati.entity.AdminMission;
import com.family.gati.entity.Mission;
import com.family.gati.repository.AdminMissionRepository;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    private final AdminMissionRepository adminMissionRepository;
    private final MissionImageService missionImageService;
    private final FamilyRepository familyRepository;
    @Override
    public List<MissionDto> findByGroupId(Integer groupId) {
        List<Mission> missions = missionRepository.findByGroupIdOrderByAdminMissionIdDesc(groupId);
        int size = missions.size();
        List<MissionDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MissionDto mission = new MissionDto.MissionDtoBuilder(missions.get(i)).build();
            result.add(mission);
        }
        return result;
    }

    public MissionDto insertMission(MissionDto missionDto) {
        return new MissionDto.MissionDtoBuilder(missionRepository.save(new Mission.MissionBuilder(missionDto).build())).build();
    }

    @Override
    public MissionDto updateMission(MissionDto missionDto) {
        return new MissionDto.MissionDtoBuilder(missionRepository.save(new Mission.MissionBuilder(missionDto).build())).build();
    }

    @Override
    public void deleteMissionById(Integer id) {
        missionRepository.deleteById(id);
    }

    @Override
    public MissionDto findById(Integer id) {
        Mission mission = missionRepository.findById(id).get();
        MissionDto missionDto = new MissionDto.MissionDtoBuilder(mission).build();
        if (mission.getCompleted() == 1) {
            missionDto.setMissionImageDtos(missionImageService.findByMissionId(mission.getId()));
        }
        return missionDto;
    }

    @Override
    public MissionDto findMissionThisWeek(Integer groupId) {
        Date date = new Date();
        AdminMission adminMission = adminMissionRepository.findByStartDateIsLessThanEqualAndEndDateGreaterThanEqual(date, date);
        Mission mission = missionRepository.findByGroupIdAndAdminMissionId(groupId, adminMission.getId());
        if (mission == null) {
            Mission newmission = new Mission(adminMission, groupId);
            newmission.setMemNumber(familyRepository.findById(groupId).getFamilyTotal());
            MissionDto missionDto = new MissionDto.MissionDtoBuilder(missionRepository.save(newmission)).build();
            return missionDto;
        }
        MissionDto missionDto = new MissionDto.MissionDtoBuilder(mission).build();
        if (mission.getCompleted() == 1) {
            missionDto.setMissionImageDtos(missionImageService.findByMissionId(mission.getId()));
        }
        return missionDto;
    }

    @Override
    public MissionDto setMissionMemNumber(MissionRegistDto missionRegistDto) {
        Mission mission = missionRepository.findById(missionRegistDto.getId()).get();
        if (mission.getCompleted() != 0) {
            return new MissionDto.MissionDtoBuilder(mission).build();
        }
        mission.setMemNumber(missionRegistDto.getMemNumber());
        mission.setCompleted(1);
        return new MissionDto.MissionDtoBuilder(missionRepository.save(mission)).build();
    }

    @Override
    public MissionDto completeMission(Integer id, String path) {
        Mission mission = missionRepository.findById(id).get();
        if (mission.getCompleted() != 1) {
            return new MissionDto.MissionDtoBuilder(mission).build();
        }
        mission.setImg(path);
        mission.setCompleted(2);
        return new MissionDto.MissionDtoBuilder(missionRepository.save(mission)).build();
    }
}