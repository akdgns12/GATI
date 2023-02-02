package com.family.gati.dto;

import com.family.gati.entity.AlbumComment;
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

    public AlbumCommentDto(AlbumComment albumComment) {
        this.id = albumComment.getId();
        this.albumId = albumComment.getAlbumId();
        this.userId = albumComment.getUserId();
        this.content = albumComment.getContent();
        this.createTime = albumComment.getCreateTime();
        this.updateTime = albumComment.getUpdateTime();
        this.nickname = albumComment.getNickname();
    }
}
