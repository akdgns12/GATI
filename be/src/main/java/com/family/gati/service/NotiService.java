package com.family.gati.service;

import com.family.gati.dto.*;
import com.family.gati.entity.Noti;
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

    public List<NotiDto> getByUserId(String userId) {
        //회원 인증 토큰
        List<Noti> noties = notiRepository.findAll();
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

        noti.setNickname(commentNotiDto.getReceiverId());
        noti.setBoardId(commentNotiDto.getBoardId());
        noti.setNickname(commentNotiDto.getSenderNickname());
        noti.setType(2);

        notiRepository.save(noti);
    }

    // 좋아요
    public void saveLike(LikeNotiDto likeNotiDto){
        Noti noti = new Noti();

        noti.setNickname(likeNotiDto.getReceiverId());
        noti.setBoardId(likeNotiDto.getBoardId());
        noti.setNickname(likeNotiDto.getSenderNickname());
        noti.setType(3);

        notiRepository.save(noti);
    }

    // 미션 시작
//    public void saveMissionStart()
    
    // 미션 완료

    @Transactional
    public void deleteNoti(String userId, int familyId){
        notiRepository.deleteByFamilyIdAndUserId(userId, familyId);
    }
}
