package com.family.gati.repository;


import com.family.gati.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
    List<FamilyMember> findAllByUserId(String userId); // findAll
    List<FamilyMember> findALlByFamilyId(int familyId);
    FamilyMember findFamilyById(int id);
    FamilyMember findMainByUserId(String userId);

    @Query(value = "SELECT id from FAMILY_MEMBER WHERE FAMILY_ID=:familyId AND USER_ID=:userId", nativeQuery = true)
    FamilyMember findFamilyMemberAlreadyExist(@Param("familyId") int familyId, @Param("userId") String userId);
    void deleteByFamilyId(int id);
}
