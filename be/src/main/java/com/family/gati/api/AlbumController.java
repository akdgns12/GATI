package com.family.gati.api;

import com.family.gati.dto.AlbumCommentDto;
import com.family.gati.dto.AlbumDto;
import com.family.gati.service.AlbumCommentService;
import com.family.gati.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@Api(tags = "Album API")
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumCommentService albumCommentService;

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getAlbumsByGroupId(@ApiParam(value = "path로 groupId 전달받음")@PathVariable("groupId") Integer groupId) {
        List<AlbumDto> findDtos = albumService.findByGroupId(groupId);
        return ResponseEntity.ok(findDtos);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getAlbumById(@ApiParam(value = "path로 id 전달받음")@PathVariable("id") Integer id) {
        AlbumDto findDto = albumService.findById(id);
        return ResponseEntity.ok(findDto);
    }

    @GetMapping("/comments/{albumId}")
    public ResponseEntity<?> getCommentsById(@ApiParam(value = "path로 boardId 전달받음")@PathVariable("albumId") Integer albumId) {
        List<AlbumCommentDto> findDtos = albumCommentService.findByAlbumId(albumId);
        return ResponseEntity.ok(findDtos);
    }

    @PostMapping("/album")
    public ResponseEntity<?> addAlbum(@RequestBody AlbumDto albumDto) {
        AlbumDto resultDto = albumService.insertAlbum(albumDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/album")
    public ResponseEntity<?> updateAlbum(@RequestBody AlbumDto albumDto){
        AlbumDto resultDto = albumService.updateAlbum(albumDto);
        return ResponseEntity.ok(resultDto);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addAlbumComment(@RequestBody AlbumCommentDto albumCommentDto) {
        AlbumCommentDto resultDto = albumCommentService.insertAlbumComment(albumCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @PutMapping("/comment")
    public ResponseEntity<?> updateAlbumComment(@RequestBody AlbumCommentDto albumCommentDto){
        AlbumCommentDto resultDto = albumCommentService.updateAlbumComment(albumCommentDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<?> deleteAlbum(@ApiParam(value = "삭제 할 albumId")@PathVariable("id") Integer id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@ApiParam(value = "삭제 할 commentId")@PathVariable("id") Integer id) {
        albumCommentService.deleteCommentById(id);
        return ResponseEntity.ok(null);
    }

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
