package com.family.gati.api;

import com.family.gati.dto.NotiDto;
import com.family.gati.service.NotiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/noti")
@Api(tags = "Notification API")
public class NotiController {

    private final NotiService notiService;

    @ApiOperation(value = "유저 알림 모두 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getNotiByUserId(@ApiParam(value = "path로 userId 전달받음")@PathVariable("userId") String userId){
        log.debug("userId: {}", userId);

        List<NotiDto> findDtos = notiService.findByUserId(userId);
        return ResponseEntity.ok(findDtos);
    }
}
