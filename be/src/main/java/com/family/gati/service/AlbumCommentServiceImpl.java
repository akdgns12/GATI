package com.family.gati.service;

import com.family.gati.dto.AlbumCommentDto;
import com.family.gati.entity.Album;
import com.family.gati.entity.AlbumComment;
import com.family.gati.repository.AlbumCommentRepository;
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
public class AlbumCommentServiceImpl implements AlbumCommentService{
    private final AlbumCommentRepository albumCommentRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Override
    public List<AlbumCommentDto> findByAlbumId(Integer albumId) {
        List<AlbumComment> albumComments = albumCommentRepository.findByAlbumId(albumId);
        int size = albumComments.size();
        List<AlbumCommentDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            AlbumCommentDto albumCommentDto = new AlbumCommentDto.AlbumCommentDtoBuilder(albumComments.get(i)).build();
            result.add(albumCommentDto);
        }
        return result;
    }

    @Override
    public AlbumCommentDto insertAlbumComment(AlbumCommentDto albumCommentDto) {
        AlbumComment albumComment = new AlbumComment.AlbumCommentBuilder(albumCommentDto).build();
        albumComment.setNickname(userRepository.findByUserId(albumComment.getUserId()).getNickName());
        Album album = albumRepository.findById(albumComment.getAlbumId()).get();
        album.plusComments(1);
        albumRepository.save(album);
        return new AlbumCommentDto.AlbumCommentDtoBuilder(albumCommentRepository.save(albumComment)).build();
    }

    public AlbumCommentDto updateAlbumComment(AlbumCommentDto albumCommentDto) {
        AlbumComment albumComment = albumCommentRepository.findById(albumCommentDto.getId()).get();
        albumComment.setContent(albumCommentDto.getContent());
        albumComment.setUpdateTime(albumCommentDto.getUpdateTime());

        return new AlbumCommentDto.AlbumCommentDtoBuilder(albumCommentRepository.save(albumComment)).build();
    }

    @Override
    public void deleteCommentById(Integer id) {
        AlbumComment albumComment = albumCommentRepository.findById(id).get();
        Album album = albumRepository.findById(albumComment.getAlbumId()).get();
        album.plusComments(-1);
        albumRepository.save(album);
        albumCommentRepository.deleteById(id);
    }

}
