package com.family.gati.service;

import com.family.gati.dto.AdminMissionDto;

import java.util.Date;
import java.util.List;

public interface AdminMissionService {
    List<AdminMissionDto> findAll();
    AdminMissionDto findById(Integer id);
    AdminMissionDto insertAdminMission(AdminMissionDto adminMissionDto);
    AdminMissionDto updateAdminMission(AdminMissionDto adminMissionDto);
    void deleteAdminMissionById(Integer id);
    AdminMissionDto findAdminMissionThisWeek(Date date);
}
