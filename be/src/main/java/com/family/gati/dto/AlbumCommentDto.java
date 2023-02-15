package com.family.gati.dto;

import com.family.gati.entity.AlbumComment;
import com.family.gati.entity.BoardComment;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AlbumCommentDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer albumId;
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

    public AlbumCommentDto(BoardComment boardComment, Integer albumId) {
        this.id = boardComment.getId();
        this.albumId = albumId;
        this.userId = boardComment.getUserId();
        this.content = boardComment.getContent();
        this.createTime = boardComment.getCreateTime();
        this.updateTime = boardComment.getUpdateTime();
        this.nickname = boardComment.getNickname();
    }

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
