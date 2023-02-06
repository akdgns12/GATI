package com.family.gati.repository;

import com.family.gati.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByGroupId(int groupId);
    Plan findById(int id);
    void deleteById(int id);
//    Plan deletePlanById(int id);
}
