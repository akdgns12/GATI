package com.family.gati.repository;

import com.family.gati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByEmail(String email);
    Optional<User> findOneByUserId(String userId);
    Optional<User> findOneByEmail(String email);
    User findByUserSeq(int userSeq);
    void deleteByUserSeq(int userSeq);

    @Query("SELECT u.refreshToken FROM User u WHERE u.userSeq=:userSeq")
    String getRefreshTokenByUserSeq(@Param("userSeq") int userSeq);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.refreshToken=:token WHERE u.userSeq=:userSeq")
    void updateRefreshToken(@Param("userSeq") int userSeq, @Param("token") String token);
}
