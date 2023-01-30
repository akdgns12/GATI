package com.family.gati.repository;

import com.family.gati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User join(User user);
    Optional<User> findByUserId(String userId);
    Optional<User> findByEmail(String email);
    User findByUserSeq(Long userSeq);
    void modfiyGroup(Map<String, Object> map);
    User modify(User user);
    User deleteByUserSeq(Long userSeq);
    // 짜여질 쿼리가
    // update main_group = mainGroup(groupId) where user_id = userId ??
}
