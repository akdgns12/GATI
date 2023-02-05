package com.family.gati.service;

import com.family.gati.entity.FamilyMember;
import com.family.gati.repository.FamilyMemberRepository;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;

    // 유저가 포함된 가족 list 조회
    public List<FamilyMember> getFamilyListByUserId(String userId){
        return familyMemberRepository.findAllByUserId(userId);
    }
}
