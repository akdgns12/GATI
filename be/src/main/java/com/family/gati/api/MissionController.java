package com.family.gati.api;

import com.family.gati.dto.MissionDto;
import com.family.gati.dto.MissionImageDto;
import com.family.gati.dto.MissionRegistDto;
import com.family.gati.dto.MissionUpdateDto;
import com.family.gati.service.MissionImageService;
import com.family.gati.service.MissionService;
import io.swagger.annotations.*;
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

    @ApiOperation(
            value = "현재 그룹의 Mission 조회"
            , notes = "GroupId를 통해 현재 그룹의 Mission들을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = MissionDto.class
                    , responseContainer = "List"
            )
//            , @ApiResponse(
//            code = 201
//            , message = "생성된 자원 정보"
//            , response = ResponseDTO.class
//            , responseContainer = "List"
//    )
//            , @ApiResponse(
//            code = 409
//            , message = "로직 수행 불가 모순 발생"
//            , response = ErrorDTO.class
//            , responseContainer = "List"
//    )
    })
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

    @ApiOperation(
            value = "Mission 등록"
            , notes = "Mission을 등록한다.")
    @PostMapping("/mission")
    public ResponseEntity<?> addMission(@RequestBody MissionRegistDto missionRegistDto) {
        MissionDto missionDto = new MissionDto();
        missionDto.setImg(missionRegistDto.getImg());
        missionDto.setMemNumber(missionRegistDto.getMemNumber());
        missionDto.setGroupId(missionRegistDto.getGroupId());
        // 이번주 미션의 아이디를 저장하는 수단 추가할 것
        missionDto.setAdminMissionId(1);
        missionDto.setCompleted(0);

        MissionDto resultDto = missionService.insertMission(missionDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Mission 수정"
            , notes = "Mission을 수정한다. 직접 사용할 일은 없을 것 같다.")
    @PutMapping("/mission")
    public ResponseEntity<?> updateMission(@RequestBody MissionUpdateDto missionUpdateDto) {
        MissionDto missionDto = new MissionDto();
        missionDto.setId(missionUpdateDto.getId());
        missionDto.setImg(missionUpdateDto.getImg());
        missionDto.setMemNumber(missionUpdateDto.getMemNumber());
        missionDto.setGroupId(missionUpdateDto.getGroupId());
        // 이번주 미션의 아이디를 저장하는 수단 추가할 것
        missionDto.setAdminMissionId(1);
        missionDto.setCompleted(0);

        MissionDto resultDto = missionService.updateMission(missionDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Mission 삭제"
            , notes = "Mission을 삭제한다.")
    @DeleteMapping("/mission/{id}")
    public ResponseEntity<?> deleteMission(@ApiParam(value = "삭제 할 missionId")@PathVariable("id") Integer id) {
        missionService.deleteMissionById(id);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "현재 그룹의 MissionImage 조회"
            , notes = "GroupId를 통해 현재 그룹의 이번주 Mission에대한 MissionImage들을 조회한다.")
    @GetMapping("/image/{groupId}")
    public ResponseEntity<?> getMissionImagesByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<MissionImageDto> resultDtos = missionImageService.findByGroupId(groupId);
        return ResponseEntity.ok(resultDtos);
    }

    @ApiOperation(
            value = "MissionImage 등록"
            , notes = "MissionImage를 등록한다.")
    @PostMapping("/image")
    public ResponseEntity<?> addMissionImage(@RequestBody MissionImageDto missionImageDto) {
        MissionImageDto resultDto = missionImageService.insertMissionImage(missionImageDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "MissionImage 수정"
            , notes = "MissionImage를 수정한다.")
    @PutMapping("/image")
    public ResponseEntity<?> updateMissionImage(@RequestBody MissionImageDto missionImageDto) {
        MissionImageDto resultDto = missionImageService.updateMissionImage(missionImageDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "MissionImage 삭제"
            , notes = "MissionImage를 삭제한다.")
    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteMissionImage(@ApiParam(value = "삭제 할 missionImageId")@PathVariable("id") Integer id) {
        missionImageService.deleteMissionImageById(id);
        return ResponseEntity.ok(null);
    }
}
