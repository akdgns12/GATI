package com.family.gati.repository;

import com.family.gati.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTagRepository extends JpaRepository<BoardTag, Integer> {
}
