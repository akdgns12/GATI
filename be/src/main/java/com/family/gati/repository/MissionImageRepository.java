package com.family.gati.repository;

import com.family.gati.entity.MissionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionImageRepository extends JpaRepository<MissionImage, Integer> {
    List<MissionImage> findByMissionId(Integer missionId);
}
