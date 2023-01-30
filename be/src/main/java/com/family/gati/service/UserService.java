package com.family.gati.service;

import com.family.gati.entity.User;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor // final, @NotNull 붙은 필드의 생성자 자동 생성해주는 롬복 어노테이션
public class UserService {

    private final UserRepository userRepository;

    public User join(User user){
        return userRepository.join(user);
    }

    public User getUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User getUserByUserSeq(Long userSeq){
        return userRepository.findByUserSeq(userSeq);
    }

    public void modifyMainGroup(Map<String, Object> map){
        userRepository.modfiyGroup(map);
    }
    public User modify(User user){
        return userRepository.modify(user);
    }

    public void deleteUser(Long userSeq){
        userRepository.deleteByUserSeq(userSeq);
    }

}
