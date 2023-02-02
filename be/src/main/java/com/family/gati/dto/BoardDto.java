package com.family.gati.dto;

import com.family.gati.entity.Board;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class BoardDto {
    private Integer id;
    private Integer groupId;
    private String userId;
    private String content;
    private String tag;
    private String img;
    private Integer likes;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer comments;
    private String nickname;

    private BoardDto(BoardDto.BoardDtoBuilder builder) {
        this.id = builder.id;
        this.groupId = builder.groupId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.tag = builder.tag;
        this.img = builder.img;
        this.likes = builder.likes;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.comments = builder.comments;
        this.nickname = builder.nickname;
    }

    public static class BoardDtoBuilder implements CommonBuilder<BoardDto> {
        private Integer id;
        private Integer groupId;
        private String userId;
        private String content;
        private String tag;
        private String img;
        private Integer likes;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Integer comments;
        private String nickname;


        public BoardDtoBuilder(Board board) {
            this.id = board.getId();
            this.groupId = board.getGroupId();
            this.userId = board.getUserId();
            this.content = board.getContent();
            this.tag = board.getTag();
            this.img = board.getImg();
            this.likes = board.getLikes();
            this.createTime = board.getCreateTime();
            this.updateTime = board.getUpdateTime();
            this.comments = board.getComments();
            this.nickname = board.getNickname();
        }

        public BoardDto build() {
            return new BoardDto(this);
        }
    }
}
