package com.family.gati.repository;


import com.family.gati.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
    List<FamilyMember> findAllByUserId(String userId); // findAll
    List<FamilyMember> findALlByFamilyId(int familyId);
    FamilyMember findFamilyById(int id);
    FamilyMember findMainByUserId(String userId);
    void deleteByFamilyId(int id);
}
