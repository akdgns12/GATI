package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.service.BoardCommentService;
import com.family.gati.service.BoardService;
import com.family.gati.service.FileService;
import com.family.gati.service.NotiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final FileService fileService;
    private final NotiService notiService;

//    @ApiOperation(
//            value = "현재 그룹의 Board 조회"
//            , notes = "GroupId를 통해 현재 그룹의 Board를 최신순으로 조회한다.")
//    @ApiResponses({
//            @ApiResponse(
//                    code = 200
//                    , message = "조회 성공"
//                    , response = BoardDto.class
//                    , responseContainer = "List"
//            )
////            , @ApiResponse(
////            code = 201
////            , message = "생성된 자원 정보"
////            , response = ResponseDTO.class
////            , responseContainer = "List"
////    )
////            , @ApiResponse(
////            code = 409
////            , message = "로직 수행 불가 모순 발생"
////            , response = ErrorDTO.class
////            , responseContainer = "List"
////    )
//    })
//    @GetMapping("/")
//    public ResponseEntity<?> getBoardsByGroupId(@RequestParam Integer groupId, @RequestParam String userId) {
//        List<BoardDto> findDtos = boardService.findByGroupId(groupId, userId);
//        return ResponseEntity.ok(findDtos);
//    }

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
    public ResponseEntity<?> getBoardsByGroupIdAndPage(@RequestParam Integer groupId, @RequestParam Integer page, @RequestParam String userId) {
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
        findDto.setBoardCommentDtos(boardCommentService.findByBoardId(id));
        return ResponseEntity.ok(findDto);
    }

//    @ApiOperation(
//            value = "Comment 조회"
//            , notes = "Board의 Id를 통해 해당 Board의 Comment를 조회한다.")
//    @GetMapping("/comments/{boardId}")
//    public ResponseEntity<?> getCommentsById(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("boardId") Integer boardId) {
//        List<BoardCommentDto> findDtos = boardCommentService.findByBoardId(boardId);
//        return ResponseEntity.ok(findDtos);
//    }

    @ApiOperation(
            value = "Board 작성"
            , notes = "Board를 작성한다.")
    @PostMapping("/board")
    public ResponseEntity<?> addBoard(@ApiParam(value = "The file to upload", required = true) @RequestPart MultipartFile file,
                                      @ApiParam(value = "The DTO object", required = true) @ModelAttribute BoardRegistDto boardRegistDto) {
        BoardDto boardDto = new BoardDto();
        String path;
        try {
            path = fileService.fileUpload(file, "board");
        } catch (Exception e) {
            // 에러코드 전송
            throw new RuntimeException(e);
        }
        boardDto.setGroupId(boardRegistDto.getGroupId());
        boardDto.setUserId(boardRegistDto.getUserId());
        boardDto.setContent(boardRegistDto.getContent());
        boardDto.setTag(boardRegistDto.getTagDtos());
        boardDto.setImg(path);
        boardDto.setLikes(0);
        boardDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardDto.setUpdateTime(new Timestamp(new Date().getTime()));
        boardDto.setComments(0);

        BoardDto resultDto = boardService.insertBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Board 수정"
            , notes = "Board를 수정한다.")
    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@ApiParam(value = "The file to upload", required = false) @RequestPart(required = false) MultipartFile file,
                                         @ApiParam(value = "The DTO object", required = true) @ModelAttribute BoardUpdateDto boardUpdateDto){
        BoardDto boardDto = new BoardDto();
        String path = null;
        if (file != null) {
            try {
                path = fileService.fileUpload(file, "board");
            } catch (Exception e) {
                // 에러코드 전송
                throw new RuntimeException(e);
            }
        }
        boardDto.setId(boardUpdateDto.getId());
        boardDto.setContent(boardUpdateDto.getContent());
        boardDto.setTag(boardUpdateDto.getTagDtos());
        boardDto.setImg(path);
        boardDto.setUpdateTime(new Timestamp(new Date().getTime()));

        BoardDto resultDto = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 작성"
            , notes = "Board의 Comment를 작성한다.")
    @PostMapping("/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardCommentRegistDto boardCommentRegistDto) {
        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setUserId(boardCommentRegistDto.getUserId());
        boardCommentDto.setBoardId(boardCommentRegistDto.getBoardId());
        boardCommentDto.setContent(boardCommentRegistDto.getContent());
        boardCommentDto.setCreateTime(new Timestamp(new Date().getTime()));
        boardCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));

        boardCommentService.insertBoardComment(boardCommentDto);
        BoardDto resultDto = boardService.findById(boardCommentDto.getBoardId(), boardCommentDto.getUserId());

        CommentNotiDto commentNotiDto = new CommentNotiDto(
                resultDto.getUserId(),
                resultDto.getId(),
                resultDto.getNickname(),
                2
        );
        notiService.saveComment(commentNotiDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 수정"
            , notes = "Board의 Comment를 수정한다.")
    @PutMapping("/comment")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardCommentUpdateDto boardCommentUpdateDto){
        BoardCommentDto boardCommentDto = new BoardCommentDto();
        boardCommentDto.setId(boardCommentUpdateDto.getId());
        boardCommentDto.setContent(boardCommentUpdateDto.getContent());
        boardCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));

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
    public ResponseEntity<?> addBoardLikes(@RequestParam Integer boardId, @RequestParam String userId) {
        if (boardService.findLikes(boardId, userId)) {
            String receiverId = boardService.findById(boardId, userId).getUserId();
            LikeNotiDto likeNotiDto = new LikeNotiDto(
                    receiverId, boardId, userId, 3
            );
            notiService.saveLike(likeNotiDto);
        };
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "Board에서 Album 저장"
            , notes = "Board에서 Album 저장한다.")
    @PostMapping("/save")
    public ResponseEntity<?> addBoardLikes(@RequestParam Integer boardId) {
        boardService.saveAlbum(boardId);
        return ResponseEntity.ok(null);
    }
}
