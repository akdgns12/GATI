package com.family.gati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.family.gati.entity.Noti;

import java.util.List;

@Repository
public interface NotiRepository extends JpaRepository<Noti, String> {
    @Query(value = "select * from notification where user_id = :userId", nativeQuery = true)
    List<Noti> findByUserId(@Param("userId") String userId);

}
