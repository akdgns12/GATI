package com.family.gati.repository;

import com.family.gati.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findByFamilyId(int id);
    void deleteByFamilyId(String id);
}
