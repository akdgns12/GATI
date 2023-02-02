package com.family.gati.dto;

import com.family.gati.entity.BoardComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class BoardCommentDto {
    private Integer id;
    private Integer boardId;
    private String userId;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String nickname;

    public BoardCommentDto(BoardComment boardComment) {
        this.id = boardComment.getId();
        this.boardId = boardComment.getBoardId();
        this.userId = boardComment.getUserId();
        this.content = boardComment.getContent();
        this.createTime = boardComment.getCreateTime();
        this.updateTime = boardComment.getUpdateTime();
        this.nickname = boardComment.getNickname();
    }
}
