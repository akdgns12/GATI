package com.family.gati.service;

import com.family.gati.dto.BoardDto;
import java.util.List;

public interface BoardService {
    List<BoardDto> findByGroupId(Integer groupId, String userId);
    BoardDto insertBoard(BoardDto boardDto);
    BoardDto findById(Integer id, String userId);
    BoardDto updateBoard(BoardDto boardDto);
    void deleteBoardById(Integer id);
    boolean findLikes(Integer boardId, String userId);
    void saveAlbum(Integer boardId);
}
