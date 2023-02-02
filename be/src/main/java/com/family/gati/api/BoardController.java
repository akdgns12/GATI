package com.family.gati.api;

import com.family.gati.dto.BoardCommentDto;
import com.family.gati.dto.BoardDto;
import com.family.gati.service.BoardCommentService;
import com.family.gati.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Api(tags = "Board API")
public class BoardController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getBoardsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<BoardDto> findDtos = boardService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoardById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        BoardDto findDto = boardService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @GetMapping("/comments/{boardId}")
    public ResponseEntity<?> getCommentsById(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("boardId") Integer boardId) {
        List<BoardCommentDto> findDtos = boardCommentService.findByBoardId(boardId);
        return ResponseEntity.ok(findDtos);
    }

    @PostMapping("/board")
    public ResponseEntity<?> addBoard(@RequestBody BoardDto boardDto) {
        BoardDto resultDto = boardService.insertBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@RequestBody BoardDto boardDto){
        BoardDto resultDto = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardCommentDto boardCommentDto) {
        BoardCommentDto resultDto = boardCommentService.insertBoardComment(boardCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/comment")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardCommentDto boardCommentDto){
        BoardCommentDto resultDto = boardCommentService.updateBoardComment(boardCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@ApiParam(value = "삭제 할 boardId")@PathVariable("id") Integer id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@ApiParam(value = "삭제 할 boardId")@PathVariable("id") Integer id) {
        boardCommentService.deleteCommentById(id);
        return ResponseEntity.ok(null);
    }
}
