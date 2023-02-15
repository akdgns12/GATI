package com.family.gati.service;

import com.family.gati.dto.BoardDto;
import com.family.gati.dto.TagDto;
import com.family.gati.entity.*;
import com.family.gati.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final AlbumRepository albumRepository;
    private final AlbumCommentRepository albumCommentRepository;
    private final AlbumLikesRepository albumLikesRepository;
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
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
        List<City> cities = cityRepository.findAll();
        boardDto.setNickname(userRepository.findByUserId(boardDto.getUserId()).getNickName());
        Board board = new Board.BoardBuilder(boardDto).build();
        HashSet<City> cityHashSet = new HashSet<>();
        for (TagDto tagDto: boardDto.getTag()) {
            BoardTag boardTag = new BoardTag(tagDto.getTagContent());
            boardTag.setBoard(board);
            board.putTag(boardTag);
            for (City city : cities) {
                if (tagDto.getTagContent().contains(city.getTag())) {
                    cityHashSet.add(city);
                }
            }
        }
        for (City city : cityHashSet) {
            city.plusTagCnt(1);
            cityRepository.save(city);
        }
        return new BoardDto.BoardDtoBuilder(boardRepository.save(board)).build();
    }

    public BoardDto updateBoard(BoardDto boardDto) {
        List<City> cities = cityRepository.findAll();
        Board board = boardRepository.findById(boardDto.getId()).get();
        board.setContent(boardDto.getContent());
        if (boardDto.getImg() != null) {
            board.setImg(boardDto.getImg());
        }
        board.setTag(new ArrayList<>());
        HashSet<City> cityHashSet = new HashSet<>();
        for (TagDto tagDto: boardDto.getTag()) {
            BoardTag boardTag = new BoardTag(tagDto.getTagContent());
            boardTag.setBoard(board);
            board.putTag(boardTag);
            for (City city : cities) {
                if (tagDto.getTagContent().contains(city.getTag())) {
                    cityHashSet.add(city);
                }
            }
        }
        for (City city : cityHashSet) {
            city.plusTagCnt(1);
            cityRepository.save(city);
        }
        if (board.getAlbumId() != null) {
            Album album = albumRepository.findById(board.getAlbumId()).get();
            album.setContent(boardDto.getContent());
            if (boardDto.getImg() != null) {
                album.setImg(boardDto.getImg());
            }
            album.setTag(new ArrayList<>());
            for (TagDto tagDto: boardDto.getTag()) {
                AlbumTag albumTag = new AlbumTag(tagDto.getTagContent());
                albumTag.setAlbum(album);
                album.putTag(albumTag);
            }
        }
        return new BoardDto.BoardDtoBuilder(boardRepository.save(board)).build();
    }

    @Override
    public void deleteBoardById(Integer id) {
        // comment 삭제 나중에 꼭 해야한다면 하겠습니다.
        Board board = boardRepository.findById(id).get();
        Integer albumId = board.getAlbumId();
        if (albumId != null) {
            Album album = albumRepository.findById(albumId).get();
            album.setBoardId(null);
            List<BoardComment> boardComments = boardCommentRepository.findByBoardId(id);
            for (BoardComment boardComment : boardComments) {
                AlbumComment albumComment = new AlbumComment(boardComment, albumId);
                albumCommentRepository.save(albumComment);
            }
            albumRepository.save(album);
        }
        boardRepository.deleteById(id);
    }

    public boolean findLikes(Integer boardId, String userId) {
        BoardLikes findBoardLikes = boardLikesRepository.findByBoardIdAndUserId(boardId, userId);
        Board board = boardRepository.findById(boardId).get();

        if (findBoardLikes == null) {
            boardLikesRepository.save(new BoardLikes(boardId, userId));
            board.plusLikes(1);
            boardRepository.save(board);
            Integer albumId = board.getAlbumId();
            if (albumId != null) {
                Album album = albumRepository.findById(albumId).get();
                album.plusLikes(1);
                albumRepository.save(album);
                albumLikesRepository.save(new AlbumLikes(albumId, userId));
            }
            return true;
        }
        boardLikesRepository.deleteById(findBoardLikes.getId());
        board.plusLikes(-1);
        boardRepository.save(board);
        return false;
    }

    @Override
    public void saveAlbum(Integer boardId) {
        Board board = boardRepository.findById(boardId).get();
        if (board.getAlbumId() != null)
            return;
        Album album = new Album(board);
        List<BoardTag> boardTags = board.getTag();
        for (BoardTag boardTag : boardTags) {
            AlbumTag albumTag = new AlbumTag(boardTag.getTag());
            albumTag.setAlbum(album);
            album.putTag(albumTag);
        }
        Integer albumId = albumRepository.save(album).getId();
        board.setAlbumId(albumId);
        boardRepository.save(board);
    }
}
