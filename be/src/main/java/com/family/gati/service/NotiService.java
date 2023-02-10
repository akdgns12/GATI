package com.family.gati.service;

import com.family.gati.dto.FamilyNotiDto;
import com.family.gati.dto.NotiDto;
import com.family.gati.entity.Noti;
import com.family.gati.repository.NotiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    // 그룹 초대
    public void saveFamilyInvite(FamilyNotiDto familyNotiDto){
        Noti noti = new Noti();
        noti.setGroupId(familyNotiDto.getId());
        noti.setGroupName(familyNotiDto.getName());
        noti.setType(familyNotiDto.getType());

        notiRepository.save(noti);
    }

    // 댓글
    public void saveComment(){

    }

    // 좋아요
    
    // 미션 시작
    
    // 미션 완료
}
