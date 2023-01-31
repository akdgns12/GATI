package com.family.gati.repository;

import com.family.gati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByEmail(String email);
    Optional<User> findOneByEmail(String email);
    User findByUserSeq(int userSeq);
    void deleteByUserSeq(int userSeq);
    User findByToken(String refreshToken);
    // 짜여질 쿼리가
    // update main_group = mainGroup(groupId) where user_id = userId ??
}
