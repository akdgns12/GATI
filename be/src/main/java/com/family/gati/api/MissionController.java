package com.family.gati.api;

import com.family.gati.dto.MissionDto;
import com.family.gati.dto.MissionImageDto;
import com.family.gati.service.MissionImageService;
import com.family.gati.service.MissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Api(tags = "Mission API")
public class MissionController {
    private final MissionService missionService;
    private final MissionImageService missionImageService;

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getMissionsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<MissionDto> findDtos = missionService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

//    @GetMapping("/mission/{id}")
//    public ResponseEntity<?> getMissionById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
//        MissionDto findDto = missionService.findById(id);
//        return ResponseEntity.ok(findDto);
//    }

    @PostMapping("/mission")
    public ResponseEntity<?> addMission(@RequestBody MissionDto missionDto) {
        MissionDto resultDto = missionService.insertMission(missionDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/mission")
    public ResponseEntity<?> updateMission(@RequestBody MissionDto missionDto) {
        MissionDto resultDto = missionService.updateMission(missionDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/mission/{id}")
    public ResponseEntity<?> deleteMission(@ApiParam(value = "삭제 할 missionId")@PathVariable("id") Integer id) {
        missionService.deleteMissionById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/image/{groupId}")
    public ResponseEntity<?> getMissionImagesByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<MissionImageDto> resultDtos = missionImageService.findByGroupId(groupId);
        return ResponseEntity.ok(resultDtos);
    }

    @PostMapping("/image")
    public ResponseEntity<?> addMissionImage(@RequestBody MissionImageDto missionImageDto) {
        MissionImageDto resultDto = missionImageService.insertMissionImage(missionImageDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/image")
    public ResponseEntity<?> updateMissionImage(@RequestBody MissionImageDto missionImageDto) {
        MissionImageDto resultDto = missionImageService.updateMissionImage(missionImageDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteMissionImage(@ApiParam(value = "삭제 할 missionImageId")@PathVariable("id") Integer id) {
        missionImageService.deleteMissionImageById(id);
        return ResponseEntity.ok(null);
    }
}
