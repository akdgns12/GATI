package com.family.gati.api;

import com.family.gati.dto.AdminMissionDto;
import com.family.gati.service.AdminMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminMissions")
@Api(tags = "AdminMission API")
public class AdminMissionController {
    private final AdminMissionService adminMissionService;

    @GetMapping("/list")
    public ResponseEntity<?> getAdminMissions() {
        List<AdminMissionDto> findDtos = adminMissionService.findAll();
        return ResponseEntity.ok(findDtos);
    }

    @GetMapping("/adminMission/{id}")
    public ResponseEntity<?> getAdminMissionById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        AdminMissionDto findDto = adminMissionService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @PostMapping("/adminMission")
    public ResponseEntity<?> addAdminMission(@RequestBody AdminMissionDto adminMissionDto) {
        AdminMissionDto resultDto = adminMissionService.insertAdminMission(adminMissionDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/adminMission")
    public ResponseEntity<?> updateAdminMission(@RequestBody AdminMissionDto adminMissionDto) {
        AdminMissionDto resultDto = adminMissionService.updateAdminMission(adminMissionDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("adminMission/{id}")
    public ResponseEntity<?> deleteAdminMission(@ApiParam(value = "삭제 할 adminMissionId")@PathVariable("id") Integer id) {
        adminMissionService.deleteAdminMissionById(id);
        return ResponseEntity.ok(null);
    }
}
