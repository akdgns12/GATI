package com.family.gati.repository;

import com.family.gati.entity.Plan;
import com.family.gati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
    List<Plan> findByGroupId(Integer groupId);
    User findByUserId(String userId);
}
