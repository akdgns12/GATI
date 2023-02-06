package com.family.gati.api;

import com.family.gati.dto.BoardCommentDto;
import com.family.gati.dto.BoardDto;
import com.family.gati.service.BoardCommentService;
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
@RequestMapping("/boards")
@Api(tags = "Board API")
public class BoardController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

    @ApiOperation(
            value = "현재 그룹의 Board 조회"
            , notes = "GroupId를 통해 현재 그룹의 Board를 최신순으로 조회한다.")
    @GetMapping("/{groupId}")
    public ResponseEntity<?> getBoardsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<BoardDto> findDtos = boardService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "Board 조회"
            , notes = "Board의 Id를 통해 해당 Board를 조회한다.")
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoardById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        BoardDto findDto = boardService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @ApiOperation(
            value = "Comment 조회"
            , notes = "Board의 Id를 통해 해당 Board의 Comment를 조회한다.")
    @GetMapping("/comments/{boardId}")
    public ResponseEntity<?> getCommentsById(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("boardId") Integer boardId) {
        List<BoardCommentDto> findDtos = boardCommentService.findByBoardId(boardId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "Board 작성"
            , notes = "Board를 작성한다.")
    @PostMapping("/board")
    public ResponseEntity<?> addBoard(@RequestBody BoardDto boardDto) {
        BoardDto resultDto = boardService.insertBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Board 수정"
            , notes = "Board를 수정한다.")
    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@RequestBody BoardDto boardDto){
        BoardDto resultDto = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 작성"
            , notes = "Board의 Comment를 작성한다.")
    @PostMapping("/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardCommentDto boardCommentDto) {
        BoardCommentDto resultDto = boardCommentService.insertBoardComment(boardCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 수정"
            , notes = "Board의 Comment를 수정한다.")
    @PutMapping("/comment")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardCommentDto boardCommentDto){
        BoardCommentDto resultDto = boardCommentService.updateBoardComment(boardCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Board 삭제"
            , notes = "Board의 Id를 통해 해당 Board를 삭제한다.")
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@ApiParam(value = "삭제 할 boardId")@PathVariable("id") Integer id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "Comment 삭제"
            , notes = "Comment의 Id를 통해 해당 Comment를 삭제한다.")
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@ApiParam(value = "삭제 할 commentId")@PathVariable("id") Integer id) {
        boardCommentService.deleteCommentById(id);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "Board의 좋아요 상태를 변경한다."
            , notes = "BoardId, 현재 user를 확인하여 좋아요 상태를 변경한다.")
    @PostMapping("/likes")
    public ResponseEntity<?> addBoardLikes(@RequestBody Integer boardId) {
        //userId 후에 token으로 얻을 것.
        boardService.findLikes(boardId, "userId");
//        if (boardService.findLikes(boardId, userId)) {
////            BoardDto boardDto = boardService.findById(boardId);
////            boardDto.setLikes(boardDto.getLikes() + 1);
////            boardService.updateBoard(boardDto);
//            return ResponseEntity.ok(null);
//        }
////        BoardDto boardDto = boardService.findById(boardId);
////        boardDto.setLikes(boardDto.getLikes() - 1);
////        boardService.updateBoard(boardDto);
        return ResponseEntity.ok(null);
    }
}
