package com.family.gati.service;

import com.family.gati.dto.BoardCommentDto;
import com.family.gati.entity.Album;
import com.family.gati.entity.Board;
import com.family.gati.entity.BoardComment;
import com.family.gati.repository.AlbumRepository;
import com.family.gati.repository.BoardCommentRepository;
import com.family.gati.repository.BoardRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService{
    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Override
    public List<BoardCommentDto> findByBoardId(Integer boardId) {
        List<BoardComment> boardComments = boardCommentRepository.findByBoardId(boardId);
        int size = boardComments.size();
        List<BoardCommentDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BoardCommentDto boardCommentDto = new BoardCommentDto.BoardCommentDtoBuilder(boardComments.get(i)).build();
            result.add(boardCommentDto);
        }
        return result;
    }

    @Override
    public BoardCommentDto insertBoardComment(BoardCommentDto boardCommentDto) {
        BoardComment boardComment = new BoardComment.BoardCommentBuilder(boardCommentDto).build();
        boardComment.setNickname(userRepository.findByUserId(boardComment.getUserId()).getNickName());
        Board board = boardRepository.findById(boardComment.getBoardId()).get();
        board.plusComments(1);
        boardRepository.save(board);
        if (board.getAlbumId() != null) {
            Album album = albumRepository.findById(board.getAlbumId()).get();
            album.plusComments(1);
            albumRepository.save(album);
        }
        return new BoardCommentDto.BoardCommentDtoBuilder(boardCommentRepository.save(boardComment)).build();
    }

    public BoardCommentDto updateBoardComment(BoardCommentDto boardCommentDto) {
        BoardComment boardComment = boardCommentRepository.findById(boardCommentDto.getId()).get();
        boardComment.setContent(boardCommentDto.getContent());
        boardComment.setUpdateTime(boardCommentDto.getUpdateTime());

        return new BoardCommentDto.BoardCommentDtoBuilder(boardCommentRepository.save(boardComment)).build();
    }

    @Override
    public void deleteCommentById(Integer id) {
        BoardComment boardComment = boardCommentRepository.findById(id).get();
        Board board = boardRepository.findById(boardComment.getBoardId()).get();
        board.plusComments(-1);
        boardRepository.save(board);
        if (board.getAlbumId() != null) {
            Album album = albumRepository.findById(board.getAlbumId()).get();
            album.plusComments(-1);
            albumRepository.save(album);
        }
        boardCommentRepository.deleteById(id);
    }

}
