package com.family.gati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import com.family.gati.entity.Noti;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotiRepository extends JpaRepository<Noti, String> {
    @Query(value = "select * from NOTIFICATION where user_id = :userId", nativeQuery = true)
    List<Noti> findByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "delete from NOTIFICATION where USER_ID = :userId AND GROUP_ID = :familyId", nativeQuery = true)
    void deleteByFamilyIdAndUserId(@Param("userId") String userId, @Param("familyId") int familyId);
}
