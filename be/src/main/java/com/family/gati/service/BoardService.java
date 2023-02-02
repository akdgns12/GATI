package com.family.gati.service;

import com.family.gati.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> findByGroupId(Integer groupId);
    void insertBoard(BoardDto boardDto);
}
