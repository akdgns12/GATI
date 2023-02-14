package com.family.gati.service;

import com.family.gati.dto.*;
import com.family.gati.entity.Mission;
import com.family.gati.entity.MissionImage;
import com.family.gati.repository.MissionImageRepository;
import com.family.gati.repository.MissionRepository;
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
    private final MissionRepository missionRepository;

    @Override
    public List<MissionImageDto> findByMissionId(Integer missionId) {
        List<MissionImage> missionImages = missionImageRepository.findByMissionId(missionId);
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
    public MissionImageDto updateMissionImage(MissionImageUpdateDto missionImageUpdateDto) {
        MissionImage missionImage = missionImageRepository.findById(missionImageUpdateDto.getId()).get();
        missionImage.setImg(missionImageUpdateDto.getImg());
        return new MissionImageDto.MissionImageDtoBuilder(missionImageRepository.save(missionImage)).build();
    }

    @Override
    public MissionDto deleteMissionImageById(Integer id) {
        Integer missionId = missionImageRepository.findById(id).get().getMissionId();
        missionImageRepository.deleteById(id);
        Mission mission = missionRepository.findById(missionId).get();
        MissionDto missionDto = new MissionDto.MissionDtoBuilder(mission).build();
        if (mission.getCompleted() == 1) {
            missionDto.setMissionImageDtos(findByMissionId(mission.getId()));
        }
        return missionDto;
    }

    @Override
    public MissionImageDto findById(Integer id) {
        return new MissionImageDto.MissionImageDtoBuilder(missionImageRepository.getById(id)).build();
    }
}
