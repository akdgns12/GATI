package com.family.gati.dto;

import com.family.gati.entity.BoardComment;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class BoardCommentDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer boardId;
    @ApiModelProperty(example = "userid")
    private String userId;
    @ApiModelProperty(example = "댓글의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp createTime;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp updateTime;
    @ApiModelProperty(example = "akdgns12")
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
