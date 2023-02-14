package com.family.gati.service;

import com.family.gati.dto.AlbumDto;
import com.family.gati.dto.TagDto;
import com.family.gati.entity.Album;
import com.family.gati.entity.AlbumLikes;
import com.family.gati.entity.AlbumTag;
import com.family.gati.repository.AlbumLikesRepository;
import com.family.gati.repository.AlbumRepository;
import com.family.gati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService{
    private final AlbumRepository albumRepository;
    private final AlbumLikesRepository albumLikesRepository;
    private final UserRepository userRepository;
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
        AlbumDto result = new AlbumDto.AlbumDtoBuilder(albumRepository.findById(id).get()).build();
        AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(id, userId);
        if (albumLikes == null) {
            result.setUserLike(0);
        }
        return result;
    }

    public AlbumDto insertAlbum(AlbumDto albumDto) {
        albumDto.setNickname(userRepository.findByUserId(albumDto.getUserId()).getNickName());
        Album album = new Album.AlbumBuilder(albumDto).build();

        for (TagDto tagDto : albumDto.getTag()) {
            AlbumTag albumTag = new AlbumTag(tagDto.getTagContent());
            albumTag.setAlbum(album);
            album.putTag(albumTag);
        }
        return new AlbumDto.AlbumDtoBuilder(albumRepository.save(album)).build();
    }

    public AlbumDto updateAlbum(AlbumDto albumDto) {
        Album album = albumRepository.findById(albumDto.getId()).get();
        album.setContent(albumDto.getContent());
        album.setImg(albumDto.getImg());
        album.setTag(new ArrayList<>());
        for (TagDto tagDto: albumDto.getTag()) {
            AlbumTag albumTag = new AlbumTag(tagDto.getTagContent());
            albumTag.setAlbum(album);
            album.putTag(albumTag);
        }
        AlbumDto resultDto = new AlbumDto.AlbumDtoBuilder(albumRepository.save(album)).build();
        AlbumLikes albumLikes = albumLikesRepository.findByAlbumIdAndUserId(album.getId(), album.getUserId());
        if (albumLikes == null) {
            resultDto.setUserLike(0);
        }
        return resultDto;
    }

    @Override
    public void deleteAlbumById(Integer id) {
        // comment 삭제 나중에 꼭 해야한다면 하겠습니다.
        albumRepository.deleteById(id);
    }

    public boolean findLikes(Integer albumId, String userId) {
        AlbumLikes findAlbumLikes = albumLikesRepository.findByAlbumIdAndUserId(albumId, userId);
        Album album = albumRepository.findById(albumId).get();

        if (findAlbumLikes == null) {
            AlbumLikes albumLikes = new AlbumLikes();
            albumLikes.setAlbumId(albumId);
            albumLikes.setUserId(userId);
            albumLikesRepository.save(albumLikes);
            album.plusLikes(1);
            albumRepository.save(album);
            return true;
        }
        albumLikesRepository.deleteById(findAlbumLikes.getId());
        album.plusLikes(-1);
        albumRepository.save(album);
        return false;
    }

}
