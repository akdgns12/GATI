package com.family.gati.service;

import com.family.gati.entity.Role;
import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
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
        /*
            token?
            아냐, 회원가입 후 로그인할 때 토큰 생성해주는게 맞지
         */
//        user.setRole(Role.USER);

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

    public void modifyMainGroup(String userId, int groupId){
        User user = userRepository.findByUserId(userId);
        user.setUserId(userId);
        user.setMainGroup(groupId);

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        User modifiedUser = userRepository.findByEmail(user.getEmail());
        System.out.println(modifiedUser);
        modifiedUser.setUserId(user.getUserId());
        modifiedUser.setEmail(user.getEmail());
        // 비밀번호도 변경가능하게?
        modifiedUser.setPassword(user.getPassword());
        modifiedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        modifiedUser.setNickName(user.getNickName());
        modifiedUser.setBirth(user.getBirth());
        modifiedUser.setPhoneNumber(user.getPhoneNumber());

        modifiedUser.setPlusMinus(user.getPlusMinus());
        modifiedUser.setUpdateTime(LocalDateTime.now());

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
}
