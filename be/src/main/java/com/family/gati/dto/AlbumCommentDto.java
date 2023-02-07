package com.family.gati.dto;

import com.family.gati.entity.AlbumComment;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AlbumCommentDto {
    private Integer id;
    private Integer albumId;
    private String userId;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String nickname;

    private AlbumCommentDto(AlbumCommentDto.AlbumCommentDtoBuilder builder) {
        this.id = builder.id;
        this.albumId = builder.albumId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.nickname = builder.nickname;
    }

    public static class AlbumCommentDtoBuilder implements CommonBuilder<AlbumCommentDto> {
        private Integer id;
        private Integer albumId;
        private String userId;
        private String content;
        private Timestamp createTime;
        private Timestamp updateTime;
        private String nickname;

        public AlbumCommentDtoBuilder(AlbumComment albumComment) {
            this.id = albumComment.getId();
            this.albumId = albumComment.getAlbumId();
            this.userId = albumComment.getUserId();
            this.content = albumComment.getContent();
            this.createTime = albumComment.getCreateTime();
            this.updateTime = albumComment.getUpdateTime();
            this.nickname = albumComment.getNickname();
        }

        public AlbumCommentDto build() {
            return new AlbumCommentDto(this);
        }
    }
}
