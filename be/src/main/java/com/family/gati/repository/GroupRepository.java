package com.family.gati.repository;

import com.family.gati.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
//    List<Group> findAllByUserId(String userId); // findAll
//    Group findMainByUserId(String userId);
    Group findByGroupId(int groupId);
    void deleteByGroupId(String groupId);
}
