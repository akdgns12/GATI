package com.family.gati.repository;

import com.family.gati.entity.AlbumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumCommentRepository extends JpaRepository<AlbumComment, Integer> {
    List<AlbumComment> findByAlbumId(Integer albumId);
}
