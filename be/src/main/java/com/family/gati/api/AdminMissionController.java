package com.family.gati.api;

import com.family.gati.dto.AdminMissionDto;
import com.family.gati.dto.AdminMissionResgistDto;
import com.family.gati.dto.AdminMissionUpdateDto;
import com.family.gati.exception.BadRequestException;
import com.family.gati.service.AdminMissionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminMissions")
@Api(tags = "AdminMission API")
public class AdminMissionController {
    private final AdminMissionService adminMissionService;

    @ApiOperation(
            value = "AdminMissions 조회"
            , notes = "모든 AdminMission들을 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = AdminMissionDto.class
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
    @GetMapping("/list")
    public ResponseEntity<?> getAdminMissions() {
        List<AdminMissionDto> findDtos = adminMissionService.findAll();
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "AdminMission 조회"
            , notes = "AdminMission의 Id를 통해 해당 AdminMission를 조회한다.")
    @GetMapping("/adminMission/{id}")
    public ResponseEntity<?> getAdminMissionById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        AdminMissionDto findDto = adminMissionService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @ApiOperation(
            value = "이번주 AdminMission 조회"
            , notes = "이번주 AdminMission을 조회한다.")
    @GetMapping("/adminMission/thisweek")
    public ResponseEntity<?> getAdminMissionThisWeek() {
        System.out.println(new Date());
        AdminMissionDto findDto = adminMissionService.findAdminMissionThisWeek(new Date());
        return ResponseEntity.ok(findDto);
    }
    @ApiOperation(
            value = "AdminMission 작성"
            , notes = "AdminMission을 작성한다.")
    @PostMapping("/adminMission")
    public ResponseEntity<?> addAdminMission(@RequestBody AdminMissionResgistDto adminMissionResgistDto) {
        System.out.println(adminMissionResgistDto.getStartDate());
        AdminMissionDto adminMissionDto = new AdminMissionDto();
        adminMissionDto.setTitle(adminMissionResgistDto.getTitle());
        adminMissionDto.setContent(adminMissionResgistDto.getContent());
        adminMissionDto.setImg(adminMissionResgistDto.getImg());
        adminMissionDto.setStartDate(adminMissionResgistDto.getStartDate());
        adminMissionDto.setEndDate(adminMissionResgistDto.getEndDate());
        adminMissionDto.setCreateTime(new Timestamp(new Date().getTime()));;
        adminMissionDto.setUpdateTime(new Timestamp(new Date().getTime()));;

        AdminMissionDto resultDto = adminMissionService.insertAdminMission(adminMissionDto);
        if (resultDto == null) {
            // 추후 ExceptionHandler로 잡을것.
            return ResponseEntity.ok("오류 : 입력 날짜에 해당하는 미션 이미 존재");
        }
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "AdminMission 수정"
            , notes = "AdminMission을 수정한다.")
    @PutMapping("/adminMission")
    public ResponseEntity<?> updateAdminMission(@RequestBody AdminMissionUpdateDto adminMissionUpdateDto) {
        AdminMissionDto adminMissionDto = new AdminMissionDto();
        adminMissionDto.setId(adminMissionUpdateDto.getId());
        adminMissionDto.setTitle(adminMissionUpdateDto.getTitle());
        adminMissionDto.setContent(adminMissionUpdateDto.getContent());
        adminMissionDto.setImg(adminMissionUpdateDto.getImg());
        adminMissionDto.setStartDate(adminMissionUpdateDto.getStartDate());
        adminMissionDto.setEndDate(adminMissionUpdateDto.getEndDate());
        adminMissionDto.setCreateTime(new Timestamp(new Date().getTime()));;
        adminMissionDto.setUpdateTime(new Timestamp(new Date().getTime()));;

        AdminMissionDto resultDto = adminMissionService.updateAdminMission(adminMissionDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "AdminMission 삭제"
            , notes = "AdminMission을 삭제한다.")
    @DeleteMapping("adminMission/{id}")
    public ResponseEntity<?> deleteAdminMission(@ApiParam(value = "삭제 할 adminMissionId")@PathVariable("id") Integer id) {
        adminMissionService.deleteAdminMissionById(id);
        return ResponseEntity.ok(null);
    }
}
