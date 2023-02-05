package com.family.gati.repository;

import com.family.gati.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findById(int id);
    Family findByName(String name);
    void deleteById(int id);
}
