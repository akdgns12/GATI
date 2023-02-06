package com.family.gati.service;

import com.family.gati.dto.AlbumDto;
import com.family.gati.entity.Album;
import com.family.gati.entity.AlbumLikes;
import com.family.gati.repository.AlbumLikesRepository;
import com.family.gati.repository.AlbumRepository;
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
    @Override
    public List<AlbumDto> findByGroupId(Integer groupId) {
        List<Album> albums = albumRepository.findByGroupId(groupId);
        int size = albums.size();
        List<AlbumDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            AlbumDto album = new AlbumDto.AlbumDtoBuilder(albums.get(i)).build();
            result.add(album);
        }
        return result;
    }

    @Override
    public AlbumDto findById(Integer id) {
        AlbumDto result = new AlbumDto.AlbumDtoBuilder(albumRepository.findById(id).get()).build();
        return result;
    }

    public AlbumDto insertAlbum(AlbumDto albumDto) {
        return new AlbumDto.AlbumDtoBuilder(albumRepository.save(new Album.AlbumBuilder(albumDto).build())).build();
    }

    public AlbumDto updateAlbum(AlbumDto albumDto) {
        return new AlbumDto.AlbumDtoBuilder(albumRepository.save(new Album.AlbumBuilder(albumDto).build())).build();
    }

    @Override
    public void deleteAlbumById(Integer id) {
        albumRepository.deleteById(id);
    }

    public boolean findLikes(Integer albumId, String userId) {
        AlbumLikes findAlbumLikes = albumLikesRepository.findByAlbumIdAndUserId(albumId, userId);

        if (findAlbumLikes == null) {
            AlbumLikes albumLikes = new AlbumLikes();
            albumLikes.setAlbumId(albumId);
            albumLikes.setUserId(userId);
            albumLikesRepository.save(albumLikes);
            return true;
        }
        albumLikesRepository.deleteById(findAlbumLikes.getId());
        return false;
    }

}
