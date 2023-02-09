package com.family.gati.service;

import com.family.gati.dto.AdminMissionDto;
import com.family.gati.entity.AdminMission;
import com.family.gati.repository.AdminMissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminMissionServiceImpl implements AdminMissionService{
    private final AdminMissionRepository adminMissionRepository;

    @Override
    public List<AdminMissionDto> findAll() {
        List<AdminMission> adminMissions = adminMissionRepository.findAll();
        int size = adminMissions.size();
        List<AdminMissionDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            AdminMissionDto adminMissionDto = new AdminMissionDto.AdminMissionDtoBuilder(adminMissions.get(i)).build();
            result.add(adminMissionDto);
        }
        return result;
    }

    @Override
    public AdminMissionDto findById(Integer id) {
        AdminMissionDto result = new AdminMissionDto.AdminMissionDtoBuilder(adminMissionRepository.findById(id).get()).build();
        return result;
    }

    @Override
    public AdminMissionDto insertAdminMission(AdminMissionDto adminMissionDto) {
        if (adminMissionRepository.findByStartDate(adminMissionDto.getStartDate()) == null) {
            return new AdminMissionDto.AdminMissionDtoBuilder(adminMissionRepository.save(new AdminMission.AdminMissionBuilder(adminMissionDto).build())).build();
        }
        return null;
    }

    @Override
    public AdminMissionDto updateAdminMission(AdminMissionDto adminMissionDto) {
        return new AdminMissionDto.AdminMissionDtoBuilder(adminMissionRepository.save(new AdminMission.AdminMissionBuilder(adminMissionDto).build())).build();
    }

    @Override
    public void deleteAdminMissionById(Integer id) {
        adminMissionRepository.deleteById(id);
    }

    @Override
    public AdminMissionDto findAdminMissionThisWeek() {
        Date date = new Date();
        AdminMission adminMission = adminMissionRepository.findByStartDateIsLessThanEqualAndEndDateGreaterThanEqual(date, date);
        return new AdminMissionDto.AdminMissionDtoBuilder(adminMission).build();
    }
}
