package com.family.gati.repository;

import com.family.gati.entity.AlbumLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumLikesRepository extends JpaRepository<AlbumLikes, Integer> {
    AlbumLikes findByAlbumIdAndUserId(Integer albumId, String userId);

}
