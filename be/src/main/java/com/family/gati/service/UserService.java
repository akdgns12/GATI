package com.family.gati.service;

import com.family.gati.dto.UserUpdateDto;
import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서만 데이터 변경하게 설정(그만큼 최적화 되어 읽는게 빨라짐)
@RequiredArgsConstructor // final, @NotNull 붙은 필드의 생성자 자동 생성해주는 롬복 어노테이션
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public void join(User user){
        validateDuplicateUser(user);
        user.setUserId(user.getUserId());
        user.setEmail(user.getEmail());

        user.setPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setNickName(user.getNickName());
        user.setBirth(user.getBirth());
        user.setPhoneNumber(user.getPhoneNumber());

        user.setPlusMinus(user.getPlusMinus());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user); // 여기서 뭔가 문제 발생..
    }

    public User getUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User getUserByUserSeq(int userSeq){
        return userRepository.findByUserSeq(userSeq);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // 메인 그룹 선택
    public void selectMainFamily(String userId, int groupId){
        User user = userRepository.findByUserId(userId);
        user.setMainGroup(groupId);

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(String userId, final UserUpdateDto request) {
        User originUser = userRepository.findByUserId(userId);

        if(originUser == null){
            log.debug("회원 못찾음: {}", originUser);
            return;
        }

        originUser.setEmail(request.getEmail());
        originUser.setPassword(request.getPassword());
        originUser.setPassword(passwordEncoder.encode(request.getPassword()));
        originUser.setNickName(request.getNickName());
        originUser.setBirth(request.getBirth());
        originUser.setPhoneNumber(request.getPhoneNumber());
        originUser.setUpdateTime(LocalDateTime.now());

        userRepository.save(originUser);
    }

    @Transactional
    public void deleteUser(int userSeq){
        userRepository.deleteByUserSeq(userSeq);
    }

    // 아이디 중복 체크
    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getUserId());
        if(findUser != null) throw new IllegalStateException("일치하는 아이디 이미 존재");
    }
}
