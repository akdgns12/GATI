package com.family.gati.repository;

import com.family.gati.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
    List<Mission> findByGroupIdOrderByAdminMissionIdDesc(Integer groupId);
    Mission findByGroupIdAndAdminMissionId(Integer groupId, Integer adminMissionId);
}
