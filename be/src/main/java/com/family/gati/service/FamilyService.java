package com.family.gati.service;

import com.family.gati.dto.FamilySignUpDto;
import com.family.gati.dto.FamilyUpdateDto;
import com.family.gati.entity.Family;
import com.family.gati.entity.FamilyMember;
import com.family.gati.entity.User;
import com.family.gati.repository.FamilyMemberRepository;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final UserRepository userRepository;

    public Family getFamilyById(int groupId){
        return familyRepository.findById(groupId);
    }


    // 유저의 메인 그룹 조회
    public Family getMainFamilyByUserId(String userId){
        User user = userRepository.findByUserId(userId);

        Family mainGroup = familyRepository.findById(user.getMainFamily());

        if(mainGroup == null){
            log.debug("메인 그룹 없음:{}");
            return null;
        }

        return mainGroup;
    }

    public void createFamily(String userId, FamilySignUpDto familySignUpDto){
        Family family = new Family();
        family.setName(familySignUpDto.getName());
        // 우리가 제공해줄 기본 이미지 or 사용자가 업로드한 이미지로
        family.setImg(familySignUpDto.getImg());
        // 생성할때 그룹 인원 1
        family.setFamilyTotal(1);
        // 로그인 한 유저 ID로 가져옴
        family.setMasterId(userId);

        familyRepository.save(family);
    }

    @Transactional
    public void updateFamily(FamilyUpdateDto familyUpdateDto) {
        int id = familyUpdateDto.getId();
        Family family = familyRepository.findById(id);

        if(family == null){
            log.debug("없는 그룹입니다: {}", family);
            return;
        }

        family.setName(familyUpdateDto.getName());
        family.setImg(familyUpdateDto.getImg());

        familyRepository.save(family);
    }

    @Transactional
    public void deleteFamily(int id){
        familyRepository.deleteById(id);
    }
}
