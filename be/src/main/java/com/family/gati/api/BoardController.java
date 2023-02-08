package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.service.BoardCommentService;
import com.family.gati.service.BoardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = BoardDto.class
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
    public ResponseEntity<?> getBoardsByGroupIdAndPage(@RequestParam Integer groupId, @RequestParam String userId) {
        List<BoardDto> findDtos = boardService.findByGroupId(groupId, userId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "현재 그룹의 Board page 조회"
            , notes = "GroupId와 page 번호(0부터 시작)를 통해 현재 그룹의 Board page를 최신순으로 12개 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = BoardDto.class
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
    @GetMapping("/page")
    public ResponseEntity<?> getBoardsByGroupId(@RequestParam Integer groupId, @RequestParam Integer page, @RequestParam String userId) {
        List<BoardDto> boardDtos = boardService.findByGroupId(groupId, userId);
        List<BoardDto> findDtos = new ArrayList<>();
        for (int i = page*12; i < Math.min((page+1)*12, boardDtos.size()); i++) {
            findDtos.add(boardDtos.get(i));
        }
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "Board 조회"
            , notes = "Board의 Id를 통해 해당 Board를 조회한다.")
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoardById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id,
                                          @RequestParam String userId) {
        BoardDto findDto = boardService.findById(id, userId);
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
    public ResponseEntity<?> addBoard(@RequestBody BoardRegistDto boardRegistDto) {
        BoardDto boardDto = new BoardDto();
        boardDto.setGroupId(boardRegistDto.getGroupId());
        boardDto.setContent(boardRegistDto.getContent());
        boardDto.setTag(boardRegistDto.getTagDtos());
        boardDto.setImg(boardRegistDto.getImg());
        boardDto.setLikes(0);
        boardDto.setComments(0);
        boardDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardDto.setUpdateTime(new Timestamp(new Date().getTime()));

        // 추후 jwt 활용
        boardDto.setUserId("userId");
        boardDto.setNickname("nickName");

        BoardDto resultDto = boardService.insertBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

//    기존 addboard
//    @PostMapping("/board2")
//    public ResponseEntity<?> addBoard2(@RequestBody BoardDto boardDto) {
//        BoardDto resultDto = boardService.insertBoard(boardDto);
//        return ResponseEntity.ok(resultDto);
//    }

    @ApiOperation(
            value = "Board 수정"
            , notes = "Board를 수정한다.")
    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@RequestBody BoardUpdateDto boardUpdateDto){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardUpdateDto.getId());
        boardDto.setGroupId(boardUpdateDto.getGroupId());
        boardDto.setContent(boardUpdateDto.getContent());
        boardDto.setTag(boardUpdateDto.getTagDtos());
        boardDto.setImg(boardUpdateDto.getImg());
        boardDto.setLikes(0);
        boardDto.setComments(0);
        boardDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardDto.setUpdateTime(new Timestamp(new Date().getTime()));

        // 추후 jwt 활용
        boardDto.setUserId("userId");
        boardDto.setNickname("nickName");

        BoardDto resultDto = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 작성"
            , notes = "Board의 Comment를 작성한다.")
    @PostMapping("/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardCommentRegistDto boardCommentRegistDto) {
        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setBoardId(boardCommentRegistDto.getBoardId());
        boardCommentDto.setContent(boardCommentRegistDto.getContent());
        boardCommentDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));
        // 추후 jwt 활용
        boardCommentDto.setUserId("userId");
        boardCommentDto.setNickname("nickName");

        BoardCommentDto resultDto = boardCommentService.insertBoardComment(boardCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 수정"
            , notes = "Board의 Comment를 수정한다.")
    @PutMapping("/comment")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardCommentUpdateDto boardCommentUpdateDto){
        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setId(boardCommentUpdateDto.getId());
        boardCommentDto.setBoardId(boardCommentUpdateDto.getBoardId());
        boardCommentDto.setContent(boardCommentUpdateDto.getContent());
        boardCommentDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));
        // 추후 jwt 활용
        boardCommentDto.setUserId("userId");
        boardCommentDto.setNickname("nickName");

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
