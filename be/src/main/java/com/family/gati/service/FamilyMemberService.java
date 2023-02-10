package com.family.gati.service;

import com.family.gati.dto.FamilyMemberDto;
import com.family.gati.dto.FamilySignUpDto;
import com.family.gati.dto.FamilyUpdateDto;
import com.family.gati.entity.Family;
import com.family.gati.entity.FamilyMember;
import com.family.gati.repository.FamilyMemberRepository;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyRepository familyRepository;

    // 유저가 포함된 가족 list 조회
    public List<FamilyMember> getFamilyListByUserId(String userId){
        return familyMemberRepository.findAllByUserId(userId);
    }

//    public void createFamilyMember(FamilyMemberDto familyMemberDto){
//        Family family = familyRepository.findByName(familySignUpDto.getName());
//
//        int id = family.getId();
//        FamilyMember familyMember = new FamilyMember();
//
//        familyMember.setFamilyId(id);
//        familyRepository.findById()
//        familyMember.setUserId("akdgns12");
//
//        familyMemberRepository.save(familyMember);
//    }

    @Transactional
    public void deleteFamilyMember(int id){
        familyMemberRepository.deleteByFamilyId(id);
    }
}
