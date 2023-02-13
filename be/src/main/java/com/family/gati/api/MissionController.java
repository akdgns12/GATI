package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.service.FileService;
import com.family.gati.service.MissionImageService;
import com.family.gati.service.MissionService;
import com.family.gati.service.NotiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Api(tags = "Mission API")
public class MissionController {
    private final MissionService missionService;
    private final MissionImageService missionImageService;
    private final FileService fileService;
    private final NotiService notiService;

    @ApiOperation(
            value = "현재 그룹의 Mission List 조회"
            , notes = "GroupId를 통해 현재 그룹의 Mission들을 최신순으로 조회한다.")
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
    @GetMapping("/list/{groupId}")
    public ResponseEntity<?> getMissionsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<MissionDto> findDtos = missionService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "현재 그룹의 이번주 미션 조회"
            , notes = "GroupId를 통해 현재 그룹의 이번주 Mission을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = MissionDto.class
            )
    })
    @GetMapping("/{groupId}")
    public ResponseEntity<?> getMissionByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        MissionDto findDto = missionService.findMissionThisWeek(groupId);
        return ResponseEntity.ok(findDto);
    }

    @ApiOperation(
            value = "Mission 완료"
            , notes = "Mission을 완료한다.")
    @PostMapping("/complete")
        public ResponseEntity<?> addMission(@ApiParam(value = "The file to upload", required = true) @RequestPart MultipartFile file,
                                            @ApiParam(value = "mission의 id", required = true) @RequestParam Integer id) {
        String path;
        try {
            path = fileService.fileUpload(file, "mission");
        } catch (Exception e) {
            // 에러코드 전송
            throw new RuntimeException(e);
        }
        MissionDto resultDto = missionService.completeMission(id, path);
        notiService.completeMission(resultDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Mission 인원수 설정"
            , notes = "Mission의 인원수를 설정한다.")
    @PutMapping("/setMemNumber")
    public ResponseEntity<?> registMissionMemNumber(@RequestBody MissionRegistDto missionRegistDto) {
        MissionDto resultDto = missionService.setMissionMemNumber(missionRegistDto);
        notiService.saveMissionStart(resultDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Mission 삭제"
            , notes = "Mission을 삭제한다.")
    @DeleteMapping("/mission/{id}")
    public ResponseEntity<?> deleteMission(@ApiParam(value = "삭제 할 missionId")@PathVariable("id") Integer id) {
        Integer groupId = missionService.findById(id).getGroupId();
        missionService.deleteMissionById(id);;
        List<MissionDto> findDtos = missionService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "MissionImage 등록"
            , notes = "MissionImage를 등록한다."
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping("/image")
    public ResponseEntity<?> addMissionImage(@ApiParam(value = "The DTO object", required = true) @ModelAttribute MissionImageRegistDto missionImageRegistDto,
                                             @ApiParam(value = "The file to upload", required = true) @RequestPart MultipartFile file) {
        MissionImageDto missionImageDto = new MissionImageDto();
        String path;
        try {
            path = fileService.fileUpload(file, "missionimg");
        } catch (Exception e) {
            // 에러코드 전송
            throw new RuntimeException(e);
        }
        missionImageDto.setMissionId(missionImageRegistDto.getMissionId());
        missionImageDto.setUserId(missionImageRegistDto.getUserId());
        missionImageDto.setImg(path);
        MissionImageDto resultDto = missionImageService.insertMissionImage(missionImageDto);
        return ResponseEntity.ok(missionService.findById(missionImageDto.getMissionId()));
    }

    @ApiOperation(
            value = "MissionImage 수정"
            , notes = "MissionImage를 수정한다.")
    @PutMapping("/image")
    public ResponseEntity<?> updateMissionImage(@ApiParam(value = "missionImage의 Id", required = true) @RequestParam Integer id,
                                                @ApiParam(value = "The file to upload", required = true) @RequestPart MultipartFile file) {
        String path;
        try {
            path = fileService.fileUpload(file, "missionimg");
        } catch (Exception e) {
            // 에러코드 전송
            throw new RuntimeException(e);
        }
        MissionImageUpdateDto missionImageUpdateDto = new MissionImageUpdateDto();
        missionImageUpdateDto.setId(id);
        missionImageUpdateDto.setImg(path);
        MissionImageDto resultDto = missionImageService.updateMissionImage(missionImageUpdateDto);
        return ResponseEntity.ok(missionService.findById(resultDto.getMissionId()));
    }

    @ApiOperation(
            value = "MissionImage 삭제"
            , notes = "MissionImage를 삭제한다.")
    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteMissionImage(@ApiParam(value = "삭제 할 missionImageId")@PathVariable("id") Integer id) {
        MissionDto missionDto = missionImageService.deleteMissionImageById(id);
        return ResponseEntity.ok(missionDto);

    }

    @GetMapping("/mission/{id}")
    public ResponseEntity<?> getMissionById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        MissionDto findDto = missionService.findById(id);
        return ResponseEntity.ok(findDto);
    }

//    @ApiOperation(
//            value = "Mission 수정"
//            , notes = "Mission을 수정한다. 직접 사용할 일은 없을 것 같다.")
//    @PutMapping("/mission")
//    public ResponseEntity<?> updateMission(@RequestBody MissionUpdateDto missionUpdateDto) {
//        MissionDto missionDto = new MissionDto();
//        missionDto.setId(missionUpdateDto.getId());
//        missionDto.setImg(missionUpdateDto.getImg());
//        missionDto.setMemNumber(missionUpdateDto.getMemNumber());
//        missionDto.setGroupId(missionUpdateDto.getGroupId());
//        // 이번주 미션의 아이디를 저장하는 수단 추가할 것
//        missionDto.setAdminMissionId(1);
//        missionDto.setCompleted(0);
//
//        MissionDto resultDto = missionService.updateMission(missionDto);
//        return ResponseEntity.ok(resultDto);
//    }

//    @ApiOperation(
//            value = "현재 그룹의 MissionImage 조회"
//            , notes = "GroupId를 통해 현재 그룹의 이번주 Mission에대한 MissionImage들을 조회한다.")
//    @GetMapping("/image/{groupId}")
//    public ResponseEntity<?> getMissionImagesByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
//        List<MissionImageDto> resultDtos = missionImageService.findByGroupId(groupId);
//        return ResponseEntity.ok(resultDtos);
//    }

}
