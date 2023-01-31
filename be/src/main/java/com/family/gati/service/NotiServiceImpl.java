package com.family.gati.service;

import com.family.gati.dto.NotiDto;
import com.family.gati.entity.Noti;
import com.family.gati.repository.NotiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class NotiServiceImpl implements NotiService {
    private final NotiRepository notiRepository;
    @Override
    public List<NotiDto> findByUserId(String userId) {
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
}
