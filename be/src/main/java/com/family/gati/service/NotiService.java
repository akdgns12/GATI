package com.family.gati.service;

import com.family.gati.dto.*;
import com.family.gati.entity.FamilyMember;
import com.family.gati.entity.Noti;
import com.family.gati.repository.FamilyMemberRepository;
import com.family.gati.repository.FamilyRepository;
import com.family.gati.repository.NotiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotiService {

    private final NotiRepository notiRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyRepository familyRepository;

    public List<NotiDto> getByUserId(String userId) {
        List<Noti> noties = notiRepository.findByUserId(userId);
        int size = noties.size();
        List<NotiDto> result = new ArrayList<>();
        for (int i=0; i<size; i++){
            NotiDto noti = new NotiDto.NotiDtoBuilder(noties.get(i)).build();
            result.add(noti);
        }
        return result;
    }

    // 그룹 초대 Notifiction에 저장
    public void saveFamilyInvite(FamilyNotiDto familyNotiDto){
        Noti noti = new Noti();
        noti.setGroupId(familyNotiDto.getGroupId());
        noti.setGroupName(familyNotiDto.getGroupName());
        noti.setNickname(familyNotiDto.getSenderNickname());
        noti.setUserId(familyNotiDto.getReceiverId());
        noti.setType(familyNotiDto.getType());

        notiRepository.save(noti);
    }

    // 댓글 Notification에 저장
    public void saveComment(CommentNotiDto commentNotiDto){
        Noti noti = new Noti();

        noti.setUserId(commentNotiDto.getReceiverId());
        noti.setBoardId(commentNotiDto.getBoardId());
        noti.setNickname(commentNotiDto.getSenderNickname());
        noti.setType(2);

        notiRepository.save(noti);
    }

    // 좋아요
    public void saveLike(LikeNotiDto likeNotiDto){
        Noti noti = new Noti();

        noti.setUserId(likeNotiDto.getReceiverId());
        noti.setBoardId(likeNotiDto.getBoardId());
        noti.setNickname(likeNotiDto.getSenderNickname());
        noti.setType(3);

        notiRepository.save(noti);
    }

    // 미션 시작
    public void saveMissionStart(MissionDto missionDto) {
        Integer groupId = missionDto.getGroupId();
        String groupName = familyRepository.findById(groupId).getName();
        List<FamilyMember> familyMembers = familyMemberRepository.findALlByFamilyId(groupId);
        for (FamilyMember familyMember: familyMembers) {
            Noti noti = new Noti();
            noti.setGroupId(groupId);
            noti.setMissionId(missionDto.getId());
            noti.setUserId(familyMember.getUserId());
            noti.setGroupName(groupName);
            noti.setType(4);
            notiRepository.save(noti);
        }
    }

    // 미션 완료
    public void completeMission(MissionDto missionDto) {
        Integer groupId = missionDto.getGroupId();
        String groupName = familyRepository.findById(groupId).getName();
        List<FamilyMember> familyMembers = familyMemberRepository.findALlByFamilyId(groupId);
        for (FamilyMember familyMember: familyMembers) {
            Noti noti = new Noti();
            noti.setGroupId(groupId);
            noti.setMissionId(missionDto.getId());
            noti.setUserId(familyMember.getUserId());
            noti.setGroupName(groupName);
            noti.setType(5);
            notiRepository.save(noti);
        }
    }

    @Transactional
    public void deleteNoti(String userId, int familyId) {
        notiRepository.deleteByFamilyIdAndUserId(userId, familyId);
    }
}
