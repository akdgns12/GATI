package com.family.gati.repository;

import com.family.gati.entity.Recommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommandRepository extends JpaRepository<Recommand, Integer> {

}
