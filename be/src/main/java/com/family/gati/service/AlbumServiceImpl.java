package com.family.gati.service;

import com.family.gati.dto.AlbumCommentDto;
import com.family.gati.dto.AlbumDto;
import com.family.gati.dto.BoardCommentDto;
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
public class AlbumServiceImpl implements AlbumService{
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final AlbumRepository albumRepository;
    private final AlbumCommentService albumCommentService;
    private final AlbumLikesRepository albumLikesRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    @Override
    public List<AlbumDto> findByGroupId(Integer groupId, String userId) {
        List<Album> albums = albumRepository.findByGroupIdOrderByCreateTime(groupId);
        int size = albums.size();
        List<AlbumDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Album album = albums.get(i);
            AlbumDto albumDto = new AlbumDto.AlbumDtoBuilder(album).build();
            AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(album.getId(), userId);
            if (albumLikes == null) {
                albumDto.setUserLike(0);
            }
            result.add(albumDto);
        }
        return result;
    }

    @Override
    public List<AlbumDto> findByGroupIdAndSearchCondition(Integer groupId, String userId, String search) {
        List<Album> albums = albumRepository.findByGroupIdOrderByCreateTime(groupId);
        int size = albums.size();
        List<AlbumDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Album album = albums.get(i);
            int flag = 0;
            for (AlbumTag albumTag : album.getTag()) {
                if (albumTag.getTag().contains(search)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                continue;
            AlbumDto albumDto = new AlbumDto.AlbumDtoBuilder(album).build();
            AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(album.getId(), userId);
            if (albumLikes == null) {
                albumDto.setUserLike(0);
            }
            result.add(albumDto);
        }
        return result;
    }

    @Override
    public AlbumDto findById(Integer id, String userId) {
        Album album = albumRepository.findById(id).get();
        AlbumDto result = new AlbumDto.AlbumDtoBuilder(album).build();
        if (album.getBoardId() != null) {
            List<BoardComment> boardComments = boardCommentRepository.findByBoardId(album.getBoardId());
            List<AlbumCommentDto> albumCommentDtos = new ArrayList<>();
            for (BoardComment boardComment:boardComments) {
                albumCommentDtos.add(new AlbumCommentDto(boardComment, id));
            }
            result.setAlbumCommentDtos(albumCommentDtos);
        }
        else {
            result.setAlbumCommentDtos(albumCommentService.findByAlbumId(id));
        }
        AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(id, userId);
        if (albumLikes == null) {
            result.setUserLike(0);
        }
        return result;
    }

    public AlbumDto insertAlbum(AlbumDto albumDto) {
        List<City> cities = cityRepository.findAll();
        albumDto.setNickname(userRepository.findByUserId(albumDto.getUserId()).getNickName());
        Album album = new Album.AlbumBuilder(albumDto).build();
        HashSet<City> cityHashSet = new HashSet<>();
        for (TagDto tagDto : albumDto.getTag()) {
            AlbumTag albumTag = new AlbumTag(tagDto.getTagContent());
            albumTag.setAlbum(album);
            album.putTag(albumTag);
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
        return new AlbumDto.AlbumDtoBuilder(albumRepository.save(album)).build();
    }

    public AlbumDto updateAlbum(AlbumDto albumDto) {
        List<City> cities = cityRepository.findAll();
        Album album = albumRepository.findById(albumDto.getId()).get();
        album.setContent(albumDto.getContent());
        if (albumDto.getImg() != null) {
            album.setImg(albumDto.getImg());
        }
        album.setTag(new ArrayList<>());
        HashSet<City> cityHashSet = new HashSet<>();
        for (TagDto tagDto: albumDto.getTag()) {
            AlbumTag albumTag = new AlbumTag(tagDto.getTagContent());
            albumTag.setAlbum(album);
            album.putTag(albumTag);
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
        AlbumDto resultDto = new AlbumDto.AlbumDtoBuilder(albumRepository.save(album)).build();
        AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(album.getId(), album.getUserId());
        if (albumLikes == null) {
            resultDto.setUserLike(0);
        }
        if (album.getBoardId() != null) {
            Board board = boardRepository.findById(album.getBoardId()).get();
            board.setContent(albumDto.getContent());
            if (albumDto.getImg() != null) {
                board.setImg(albumDto.getImg());
            }
            board.setTag(new ArrayList<>());
            for (TagDto tagDto: albumDto.getTag()) {
                BoardTag boardTag = new BoardTag(tagDto.getTagContent());
                boardTag.setBoard(board);
                board.putTag(boardTag);
            }
            boardRepository.save(board);
        }
        return resultDto;
    }

    @Override
    public void deleteAlbumById(Integer id) {
        // comment 삭제 나중에 꼭 해야한다면 하겠습니다.
        Album album = albumRepository.findById(id).get();
        Integer boardId = album.getBoardId();
        if (boardId != null) {
            Board board = boardRepository.findById(boardId).get();
            board.setAlbumId(null);
            boardRepository.save(board);
        }
        albumRepository.deleteById(id);
    }

    public boolean findLikes(Integer albumId, String userId) {
        AlbumLikes findAlbumLikes = albumLikesRepository.findByAlbumIdAndUserId(albumId, userId);
        Album album = albumRepository.findById(albumId).get();

        if (findAlbumLikes == null) {
            albumLikesRepository.save(new AlbumLikes(albumId, userId));
            album.plusLikes(1);
            albumRepository.save(album);
            Integer boardId = album.getBoardId();
            if (boardId != null) {
                Board board = boardRepository.findById(boardId).get();
                board.plusLikes(1);
                boardRepository.save(board);
                boardLikesRepository.save(new BoardLikes(boardId, userId));
            }
            return true;
        }
        albumLikesRepository.deleteById(findAlbumLikes.getId());
        album.plusLikes(-1);
        albumRepository.save(album);
        return false;
    }

}
