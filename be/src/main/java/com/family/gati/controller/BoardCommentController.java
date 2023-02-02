package com.family.gati.controller;

import com.family.gati.dto.BoardCommentDto;
import com.family.gati.service.BoardCommentService;
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
@RequestMapping("/boardComment")
@Api(tags = "BoardComment API")
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardCommentByBoardId(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("boardId") Integer boardId) {
        List<BoardCommentDto> findDtos = boardCommentService.findByBoardId(boardId);
        return ResponseEntity.ok(findDtos);
    }

}
