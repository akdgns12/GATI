package com.family.gati.repository;

import com.family.gati.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikesRepository extends JpaRepository<BoardLikes, Integer> {
    BoardLikes findByBoardIdAndUserId(Integer boardId, String userId);
    Integer countBoardLikesByBoardIdAndUserId(Integer boardId, String userId);

}
