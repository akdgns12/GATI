package com.family.gati.controller;

import com.family.gati.dto.BoardDto;
import com.family.gati.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = "Board API")

public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getBoardByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<BoardDto> findDtos = boardService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

}
