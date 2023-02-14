package com.family.gati.repository;

import com.family.gati.entity.Recommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommandRepository extends JpaRepository<Recommand, Integer> {
    List<Recommand> findAllByAreacodeOrderBySigungucode(int areacode);
    List<Recommand> findAllByAreacodeAndSigungucode(int areacode, int sigungucode);
}
