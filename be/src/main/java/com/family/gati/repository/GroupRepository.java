package com.family.gati.repository;

import com.family.gati.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByUserId(String userId);
    Group findMainByUserId(String userId);
    Group findByGroupId(int groupId);
    Group createNewGroup(Group group);
    Group modifyGroupByUserId(Group group);
    void deleteByGroupId(String groupId);
}
