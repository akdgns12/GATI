package com.family.gati.dto;

import com.family.gati.entity.Album;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AlbumDto {
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

    private AlbumDto(AlbumDto.AlbumDtoBuilder builder) {
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

    public static class AlbumDtoBuilder implements CommonBuilder<AlbumDto> {
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


        public AlbumDtoBuilder(Album album) {
            this.id = album.getId();
            this.groupId = album.getGroupId();
            this.userId = album.getUserId();
            this.content = album.getContent();
            this.tag = album.getTag();
            this.img = album.getImg();
            this.likes = album.getLikes();
            this.createTime = album.getCreateTime();
            this.updateTime = album.getUpdateTime();
            this.comments = album.getComments();
            this.nickname = album.getNickname();
        }

        public AlbumDto build() {
            return new AlbumDto(this);
        }
    }
}
