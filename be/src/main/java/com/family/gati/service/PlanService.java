package com.family.gati.service;

import com.family.gati.entity.Plan;
import com.family.gati.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Transactional
    public PlanResponseDto save(PlanResponseDto dto, String email){
//        회원 존재 여부 user 참조
//        User user = userRepository.인증함수(parameter)
//                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Plan plan = dto.toEntity(user);
        planRepository.save(plan);
        return new PlanResponseDto(plan);
    }
}
