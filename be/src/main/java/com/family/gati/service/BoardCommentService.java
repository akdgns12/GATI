package com.family.gati.service;

import com.family.gati.dto.BoardCommentDto;

import java.util.List;

public interface BoardCommentService {
    List<BoardCommentDto> findByBoardId(Integer boardId);
    BoardCommentDto insertBoardComment(BoardCommentDto boardCommentDto);
    BoardCommentDto updateBoardComment(BoardCommentDto boardCommentDto);
    void deleteCommentById(Integer id);
}
