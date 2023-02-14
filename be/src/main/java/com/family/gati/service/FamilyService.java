package com.family.gati.service;

import com.family.gati.dto.FamilyDto;
import com.family.gati.dto.FamilyInviteDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final NotiService notiService;

    public Family getFamilyById(int familyId){
        return familyRepository.findById(familyId);
    }

    // 유저의 메인 그룹 조회
    public Family getMainFamilyByUserId(String userId){
        User user = userRepository.findByUserId(userId);

        Family mainFamily = familyRepository.findById(user.getMainFamily());

        if(mainFamily == null){
            return null;
        }

        return mainFamily;
    }

    public Family createFamily(FamilyDto familyDto){
        Family family = new Family();

        family.setName(familyDto.getName());
        // 우리가 제공해줄 기본 이미지 or 사용자가 업로드한 이미지로
        family.setImg(familyDto.getImg());
        // 생성할때 그룹 인원 1
        family.setFamilyTotal(1);
        // 로그인 한 유저 ID로 가져옴
        family.setMasterId(familyDto.getMasterId());

        family = familyRepository.save(family);
        int familyId = family.getId();

        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyId(familyId);
        familyMember.setUserId(familyDto.getMasterId());

        familyMemberRepository.save(familyMember);

        return family;
   }

    public Family getByMasterId(String masterId){
        return familyRepository.findByMasterId(masterId);
    }

    @Transactional
    public Family updateFamily(FamilyDto familyDto) {
        int id = familyDto.getId();
        Family family = familyRepository.findById(id);

        family.setName(familyDto.getName());
        family.setImg(familyDto.getImg());
        System.out.println(family);
        familyRepository.save(family);

        return family;
    }

    @Transactional
    public void deleteFamily(int id){
        familyRepository.deleteById(id);
    }

    public void acceptInvite(FamilyInviteDto familyInviteDto){
        int familyId = familyInviteDto.getFamilyId();
        String userId = familyInviteDto.getReceiverId();
        Family family = familyRepository.findById(familyId);

        // 기존 멤버 수 +1
        family.setFamilyTotal(family.getFamilyTotal() + 1);
        familyRepository.save(family);

        // familyMember 테이블에 user 추가
        FamilyMember familyMember = new FamilyMember();
        familyMember.setUserId(userId);
        familyMember.setFamilyId(familyId);

        familyMemberRepository.save(familyMember);

        // 초대받은 유저의 mainFamily가 없다면 수락과 동시에 Main그룹으로 설정
        User user = userService.getUserByUserId(userId);

        if(user.getMainFamily() == null){
            user.setMainFamily(familyId);
            userRepository.save(user);
        }

        // 초대 수락 후 Notification 테이블에서 알림 제거
        notiService.deleteNoti(familyInviteDto.getReceiverId(), familyInviteDto.getFamilyId());
    }
}
