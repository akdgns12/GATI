package com.family.gati.dto;

import com.family.gati.entity.BoardComment;
import com.family.gati.util.CommonBuilder;
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

    private BoardCommentDto(BoardCommentDto.BoardCommentDtoBuilder builder) {
        this.id = builder.id;
        this.boardId = builder.boardId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.nickname = builder.nickname;
    }

    public static class BoardCommentDtoBuilder implements CommonBuilder<BoardCommentDto> {
        private Integer id;
        private Integer boardId;
        private String userId;
        private String content;
        private Timestamp createTime;
        private Timestamp updateTime;
        private String nickname;

        public BoardCommentDtoBuilder(BoardComment boardComment) {
            this.id = boardComment.getId();
            this.boardId = boardComment.getBoardId();
            this.userId = boardComment.getUserId();
            this.content = boardComment.getContent();
            this.createTime = boardComment.getCreateTime();
            this.updateTime = boardComment.getUpdateTime();
            this.nickname = boardComment.getNickname();
        }

        public BoardCommentDto build() {
            return new BoardCommentDto(this);
        }
    }
}
