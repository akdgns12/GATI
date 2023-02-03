package com.family.gati.service;

import com.family.gati.dto.BoardDto;
import com.family.gati.entity.Board;
import com.family.gati.entity.BoardLikes;
import com.family.gati.repository.BoardLikesRepository;
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
    private final BoardLikesRepository boardLikesRepository;
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

    @Override
    public BoardDto findById(Integer id) {
        BoardDto result = new BoardDto.BoardDtoBuilder(boardRepository.findById(id).get()).build();
        return result;
    }

    public BoardDto insertBoard(BoardDto boardDto) {
        return new BoardDto.BoardDtoBuilder(boardRepository.save(new Board.BoardBuilder(boardDto).build())).build();
    }

    public BoardDto updateBoard(BoardDto boardDto) {
        return new BoardDto.BoardDtoBuilder(boardRepository.save(new Board.BoardBuilder(boardDto).build())).build();
    }

    @Override
    public void deleteBoardById(Integer id) {
        boardRepository.deleteById(id);
    }

    public boolean findLikes(Integer boardId, String userId) {
//        BoardLikes boardLikes = boardLikesRepository.findByBoardIdAndUserId(boardId, userId);
        BoardLikes boardlike = new BoardLikes();
        boardlike.setBoardId(boardId);
        boardlike.setUserId(userId);
        boardLikesRepository.save(boardlike);
//        if (boardLikes == null) {
//            BoardLikes boardlike = new BoardLikes();
//            boardlike.setBoardId(boardId);
//            boardlike.setUserId(userId);
//            boardLikesRepository.save(boardlike);
//            return true;
//        }
//        boardLikesRepository.deleteById(boardLikes.getId());
        return false;
    }

}
