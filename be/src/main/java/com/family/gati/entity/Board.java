package com.family.gati.entity;

import com.family.gati.dto.BoardDto;
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
public class Board {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "GROUP_ID", nullable = false)
    private Integer groupId;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

    @Column(name = "TAG", nullable = false, length = 500)
    private String tag;

    @Column(name = "IMG", nullable = false, length = 500)
    private String img;

    @Column(name = "LIKES", nullable = false)
    private Integer likes;

    @Column(name = "CREATE_TIME", nullable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME", nullable = false)
    private Timestamp updateTime;

    @Column(name = "COMMENTS", nullable = false)
    private Integer comments;

    @Column(name = "NICKNAME", nullable = false, length = 20)
    private String nickname;

    private Board(BoardBuilder builder) {
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

    public static class BoardBuilder implements CommonBuilder<Board> {
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


        public BoardBuilder(BoardDto boardDto) {
            this.id = boardDto.getId();
            this.groupId = boardDto.getGroupId();
            this.userId = boardDto.getUserId();
            this.content = boardDto.getContent();
            this.tag = boardDto.getTag();
            this.img = boardDto.getImg();
            this.likes = boardDto.getLikes();
            this.createTime = boardDto.getCreateTime();
            this.updateTime = boardDto.getUpdateTime();
            this.comments = boardDto.getComments();
            this.nickname = boardDto.getNickname();
        }

        public Board build() {
            return new Board(this);
        }
    }

}