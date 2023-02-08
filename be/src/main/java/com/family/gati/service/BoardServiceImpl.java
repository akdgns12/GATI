package com.family.gati.service;

import com.family.gati.dto.BoardDto;
import com.family.gati.dto.TagDto;
import com.family.gati.entity.Board;
import com.family.gati.entity.BoardLikes;
import com.family.gati.entity.BoardTag;
import com.family.gati.repository.BoardCommentRepository;
import com.family.gati.repository.BoardLikesRepository;
import com.family.gati.repository.BoardRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final UserRepository userRepository;
    @Override
    public List<BoardDto> findByGroupId(Integer groupId, String userId) {
        List<Board> boards = boardRepository.findByGroupIdOrderByCreateTimeDesc(groupId);
        int size = boards.size();
        List<BoardDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Board board = boards.get(i);
            BoardDto boardDto = new BoardDto.BoardDtoBuilder(board).build();
            BoardLikes findBoardLikes = boardLikesRepository.findByBoardIdAndUserId(board.getId(), userId);
            if (findBoardLikes == null) {
                boardDto.setUserLike(0);
            }
            result.add(boardDto);
        }
        return result;
    }

    @Override
    public BoardDto findById(Integer id, String userId) {
        BoardDto result = new BoardDto.BoardDtoBuilder(boardRepository.findById(id).get()).build();
        BoardLikes findBoardLikes = boardLikesRepository.findByBoardIdAndUserId(id, userId);
        if (findBoardLikes == null) {
            result.setUserLike(0);
        }
        return result;
    }

    public BoardDto insertBoard(BoardDto boardDto) {
        boardDto.setNickname(userRepository.findByUserId(boardDto.getUserId()).getNickName());
        Board board = new Board.BoardBuilder(boardDto).build();
        for (TagDto tagDto: boardDto.getTag()) {
            BoardTag boardTag = new BoardTag(tagDto.getTagContent());
            boardTag.setBoard(board);
            board.putTag(boardTag);
        }
        return new BoardDto.BoardDtoBuilder(boardRepository.save(board)).build();
    }

    public BoardDto updateBoard(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();
        board.setContent(boardDto.getContent());
        board.setImg(boardDto.getImg());
        board.setTag(new ArrayList<>());
        for (TagDto tagDto: boardDto.getTag()) {
            BoardTag boardTag = new BoardTag(tagDto.getTagContent());
            boardTag.setBoard(board);
            board.putTag(boardTag);
        }
        return new BoardDto.BoardDtoBuilder(boardRepository.save(board)).build();
    }

    @Override
    public void deleteBoardById(Integer id) {
        // comment 삭제 나중에 꼭 해야한다면 하겠습니다.
        boardRepository.deleteById(id);
    }

    public boolean findLikes(Integer boardId, String userId) {
        BoardLikes findBoardLikes = boardLikesRepository.findByBoardIdAndUserId(boardId, userId);
        Board board = boardRepository.findById(boardId).get();

        if (findBoardLikes == null) {
            BoardLikes boardlikes = new BoardLikes();
            boardlikes.setBoardId(boardId);
            boardlikes.setUserId(userId);
            boardLikesRepository.save(boardlikes);
            board.plusLikes(1);
            boardRepository.save(board);
            return true;
        }
        boardLikesRepository.deleteById(findBoardLikes.getId());
        board.plusLikes(-1);
        boardRepository.save(board);
        return false;
    }

}
