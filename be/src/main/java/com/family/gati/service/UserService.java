package com.family.gati.service;

import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import com.family.gati.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서만 데이터 변경하게 설정(그만큼 최적화 되어 읽는게 빨라짐)
@RequiredArgsConstructor // final, @NotNull 붙은 필드의 생성자 자동 생성해주는 롬복 어노테이션
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void join(User user){
        validateDuplicateUser(user);
        user.setUserId(user.getUserId());
        user.setEmail(user.getEmail());

        user.setPassword(user.getPassword());
        user.encodePassword(passwordEncoder);

        user.setNickName(user.getNickName());
        user.setBirth(user.getBirth());
        user.setPhoneNumber(user.getPhoneNumber());

        user.setPlusMinus(user.getPlusMinus());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        /*
            token?
            아냐, 회원가입 후 로그인할 때 토큰 생성해주는게 맞지
         */
        user.setRole(Role.USER);

        userRepository.save(user);
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

    public void modifyMainGroup(String userId, int groupId){
        User user = userRepository.findByUserId(userId);
        user.setUserId(userId);
        user.setMainGroup(groupId);

        userRepository.save(user);
    }

    public void updateUser(User user) {
        user.setUserId(user.getUserId());
        user.setPassword(user.getPassword());
        user.setBirth(user.getBirth());
        user.setEmail(user.getEmail());
        user.setNickName(user.getNickName());
        user.setMainGroup(user.getMainGroup());
        user.setUpdateTime(LocalDateTime.now());
        user.setNickName(user.getNickName());

        userRepository.save(user);
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

    // 이메일 중복 체크
    private void validateDuplicateEmail(String email){
        User findUser = userRepository.findByEmail(email);
        if(findUser != null) throw new IllegalStateException(("이미 존재하는 이메일"));
    }
}
