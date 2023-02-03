package com.family.gati.service;

import com.family.gati.dto.AlbumCommentDto;

import java.util.List;

public interface AlbumCommentService {
    List<AlbumCommentDto> findByAlbumId(Integer albumId);
    AlbumCommentDto insertAlbumComment(AlbumCommentDto albumCommentDto);
    AlbumCommentDto updateAlbumComment(AlbumCommentDto albumCommentDto);
    void deleteCommentById(Integer id);
}
