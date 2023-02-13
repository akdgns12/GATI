package com.family.gati.repository;

import com.family.gati.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findById(int id);
    Family findByName(String name);
    Family findByMasterId(String masterId);
    List<Family> findAllByUserId(String userId);
    void deleteById(int id);
}
