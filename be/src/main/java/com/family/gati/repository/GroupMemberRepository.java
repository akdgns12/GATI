package com.family.gati.repository;

import com.family.gati.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findAllByUserId(String userId); // findAll
    GroupMember findMainByUserId(String userId);
}
