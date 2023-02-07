package com.family.gati.service;

import com.family.gati.dto.FileDto;
import com.family.gati.entity.FileEntity;
import com.family.gati.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(FileDto fileDto){
        FileEntity fileEntity = new FileEntity(fileDto.getTitle(), fileDto.getUrl());
        fileRepository.save(fileEntity);
    }

    public List<FileEntity> getFiles(){
        List<FileEntity> all = fileRepository.findAll();
        return all;
    }
}
