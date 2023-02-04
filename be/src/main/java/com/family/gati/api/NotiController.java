package com.family.gati.api;

import com.family.gati.dto.NotiDto;
import com.family.gati.service.NotiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noti")
@Api(tags = "Notification API")
public class NotiController {
    private final NotiService notiService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getNotiByUserId(@ApiParam(value = "path로 userId 전달받음")@PathVariable("userId") String userId){
        List<NotiDto> findDtos = notiService.findByUserId(userId);
        return ResponseEntity.ok(findDtos);
    }
}
