package com.family.gati.service;

import com.family.gati.dto.BoardDto;
import com.family.gati.entity.Board;
import com.family.gati.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    @Override
    public List<BoardDto> findByGroupId(Integer groupId) {
        List<Board> boards = boardRepository.findByGroupId(groupId);
        int size = boards.size();
        List<BoardDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BoardDto board = new BoardDto.BoardDtoBuilder(boards.get(i)).build();
            result.add(board);
        }
        return result;
    }

    public void insertBoard(BoardDto boardDto) {
        boardRepository.save(new Board.BoardBuilder(boardDto).build());
    }
}
