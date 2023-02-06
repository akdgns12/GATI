package com.family.gati.entity;

import com.family.gati.dto.BoardCommentDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardComment {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "BOARD_ID", nullable = false, length = 20)
    private Integer boardId;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

    @Column(name = "CREATE_TIME", nullable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME", nullable = false)
    private Timestamp updateTime;

    @Column(name = "NICKNAME", nullable = false, length = 20)
    private String nickname;

    private BoardComment(BoardCommentBuilder builder) {
        this.id = builder.id;
        this.boardId = builder.boardId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.nickname = builder.nickname;
    }

    public static class BoardCommentBuilder implements CommonBuilder<BoardComment> {
        private Integer id;
        private Integer boardId;
        private String userId;
        private String content;
        private Timestamp createTime;
        private Timestamp updateTime;
        private String nickname;

        public BoardCommentBuilder(BoardCommentDto boardCommentDto) {
            this.id = boardCommentDto.getId();
            this.boardId = boardCommentDto.getBoardId();
            this.userId = boardCommentDto.getUserId();
            this.content = boardCommentDto.getContent();
            this.createTime = boardCommentDto.getCreateTime();
            this.updateTime = boardCommentDto.getUpdateTime();
            this.nickname = boardCommentDto.getNickname();
        }

        public BoardComment build() {
            return new BoardComment(this);
        }
    }
}
