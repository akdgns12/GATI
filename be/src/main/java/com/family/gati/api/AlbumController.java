package com.family.gati.api;

import com.family.gati.dto.*;
import com.family.gati.service.AlbumCommentService;
import com.family.gati.service.AlbumService;
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
@RequestMapping("/albums")
@Api(tags = "Album API")
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumCommentService albumCommentService;

    @ApiOperation(
            value = "현재 그룹의 Album 조회"
            , notes = "GroupId를 통해 현재 그룹의 Album을 최신순으로 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = AlbumDto.class
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
    public ResponseEntity<?> getAlbumsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<AlbumDto> findDtos = albumService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "현재 그룹의 Album page 조회"
            , notes = "GroupId와 page 번호(0부터 시작)를 통해 현재 그룹의 Album page를 최신순으로 12개 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = AlbumDto.class
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
    public ResponseEntity<?> getAlbumsByGroupIdAndPage(@RequestParam Integer groupId, @RequestParam Integer page) {
        List<AlbumDto> albumDtos = albumService.findByGroupId(groupId);
        List<AlbumDto> findDtos = new ArrayList<>();
        for (int i = page*12; i < Math.min((page+1)*12, albumDtos.size()); i++) {
            findDtos.add(albumDtos.get(i));
        }
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "Album 조회"
            , notes = "Album의 Id를 통해 해당 Album을 조회한다.")
    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        AlbumDto findDto = albumService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @ApiOperation(
            value = "Comment 조회"
            , notes = "Album의 Id를 통해 해당 Album의 Comment를 조회한다.")
    @GetMapping("/comments/{albumId}")
    public ResponseEntity<?> getCommentsById(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("albumId") Integer albumId) {
        List<AlbumCommentDto> findDtos = albumCommentService.findByAlbumId(albumId);
        return ResponseEntity.ok(findDtos);
    }

    @ApiOperation(
            value = "Album 작성"
            , notes = "Album을 작성한다.")
    @PostMapping("/album")
    public ResponseEntity<?> addAlbum(@RequestBody AlbumRegistDto albumRegistDto) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setGroupId(albumRegistDto.getGroupId());
        albumDto.setContent(albumRegistDto.getContent());
        albumDto.setTag(albumRegistDto.getTag());
        albumDto.setImg(albumRegistDto.getImg());
        albumDto.setLikes(0);
        albumDto.setComments(0);
        albumDto.setCreateTime(new Timestamp(new Date().getTime()));
        albumDto.setUpdateTime(new Timestamp(new Date().getTime()));

        // 추후 jwt 활용
        albumDto.setUserId("userId");
        albumDto.setNickname("nickName");

        AlbumDto resultDto = albumService.insertAlbum(albumDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Album 수정"
            , notes = "Album을 수정한다.")
    @PutMapping("/album")
    public ResponseEntity<?> updateAlbum(@RequestBody AlbumUpdateDto albumUpdateDto){
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(albumUpdateDto.getId());
        albumDto.setGroupId(albumUpdateDto.getGroupId());
        albumDto.setContent(albumUpdateDto.getContent());
        albumDto.setTag(albumUpdateDto.getTag());
        albumDto.setImg(albumUpdateDto.getImg());
        albumDto.setLikes(0);
        albumDto.setComments(0);
        albumDto.setCreateTime(new Timestamp(new Date().getTime()));
        albumDto.setUpdateTime(new Timestamp(new Date().getTime()));

        // 추후 jwt 활용
        albumDto.setUserId("userId");
        albumDto.setNickname("nickName");

        AlbumDto resultDto = albumService.insertAlbum(albumDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 작성"
            , notes = "Album의 Comment를 작성한다.")
    @PostMapping("/comment")
    public ResponseEntity<?> addAlbumComment(@RequestBody AlbumCommentRegistDto albumCommentRegistDto) {
        AlbumCommentDto albumCommentDto = new AlbumCommentDto();
        albumCommentDto.setAlbumId(albumCommentRegistDto.getBoardId());
        albumCommentDto.setContent(albumCommentRegistDto.getContent());
        albumCommentDto.setCreateTime(new Timestamp(new Date().getTime()));
        albumCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));
        // 추후 jwt 활용
        albumCommentDto.setUserId("userId");
        albumCommentDto.setNickname("nickName");

        AlbumCommentDto resultDto = albumCommentService.insertAlbumComment(albumCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Comment 수정"
            , notes = "Album의 Comment를 수정한다.")
    @PutMapping("/comment")
    public ResponseEntity<?> updateAlbumComment(@RequestBody AlbumCommentUpdateDto albumCommentUpdateDto){
        AlbumCommentDto albumCommentDto = new AlbumCommentDto();
        albumCommentDto.setId(albumCommentUpdateDto.getId());
        albumCommentDto.setAlbumId(albumCommentUpdateDto.getBoardId());
        albumCommentDto.setContent(albumCommentUpdateDto.getContent());
        albumCommentDto.setCreateTime(new Timestamp(new Date().getTime()));
        albumCommentDto.setUpdateTime(new Timestamp(new Date().getTime()));
        // 추후 jwt 활용
        albumCommentDto.setUserId("userId");
        albumCommentDto.setNickname("nickName");

        AlbumCommentDto resultDto = albumCommentService.insertAlbumComment(albumCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @ApiOperation(
            value = "Album 삭제"
            , notes = "Album의 Id를 통해 해당 Album을 삭제한다.")
    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@ApiParam(value = "삭제 할 albumId")@PathVariable("id") Integer id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "Comment 삭제"
            , notes = "Comment의 Id를 통해 해당 Comment를 삭제한다.")
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@ApiParam(value = "삭제 할 commentId")@PathVariable("id") Integer id) {
        albumCommentService.deleteCommentById(id);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(
            value = "Album의 좋아요 상태를 변경한다."
            , notes = "AlbumId, 현재 user를 확인하여 좋아요 상태를 변경한다.")
    @PostMapping("/likes")
    public ResponseEntity<?> addAlbumLikes(@RequestBody Integer albumId) {
        //userId 후에 token으로 얻을 것.
        albumService.findLikes(albumId, "userId");
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
