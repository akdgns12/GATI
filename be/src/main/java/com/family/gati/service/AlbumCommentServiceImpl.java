package com.family.gati.service;

import com.family.gati.dto.AlbumCommentDto;
import com.family.gati.entity.AlbumComment;
import com.family.gati.repository.AlbumCommentRepository;
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
        return new AlbumCommentDto.AlbumCommentDtoBuilder(albumCommentRepository.save(new AlbumComment.AlbumCommentBuilder(albumCommentDto).build())).build();
    }

    public AlbumCommentDto updateAlbumComment(AlbumCommentDto albumCommentDto) {
        return new AlbumCommentDto.AlbumCommentDtoBuilder(albumCommentRepository.save(new AlbumComment.AlbumCommentBuilder(albumCommentDto).build())).build();

    }

    @Override
    public void deleteCommentById(Integer id) {
        albumCommentRepository.deleteById(id);
    }

}
