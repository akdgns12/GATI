package com.family.gati.service;

import com.family.gati.dto.MissionImageDto;
import com.family.gati.entity.MissionImage;
import com.family.gati.repository.MissionImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MissionImageServiceImpl implements MissionImageService{
    private final MissionImageRepository missionImageRepository;

    @Override
    public List<MissionImageDto> findByGroupId(Integer groupId) {
        List<MissionImage> missionImages = missionImageRepository.findByGroupId(groupId);
        int size = missionImages.size();
        List<MissionImageDto> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MissionImageDto missionImage = new MissionImageDto.MissionImageDtoBuilder(missionImages.get(i)).build();
            result.add(missionImage);
        }
        return result;
    }

    @Override
    public MissionImageDto insertMissionImage(MissionImageDto missionImageDto) {
        return new MissionImageDto.MissionImageDtoBuilder(missionImageRepository.save(new MissionImage.MissionImageBuilder(missionImageDto).build())).build();
    }

    @Override
    public MissionImageDto updateMissionImage(MissionImageDto missionImageDto) {
        return new MissionImageDto.MissionImageDtoBuilder(missionImageRepository.save(new MissionImage.MissionImageBuilder(missionImageDto).build())).build();
    }

    @Override
    public void deleteMissionImageById(Integer id) {
        missionImageRepository.deleteById(id);
    }
}
