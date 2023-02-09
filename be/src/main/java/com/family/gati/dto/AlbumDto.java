package com.family.gati.dto;

import com.family.gati.entity.Album;
import com.family.gati.entity.AlbumTag;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlbumDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "userid")
    private String userId;
    @ApiModelProperty(example = "앨범의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "['부산', '울산']")
    private List<TagDto> tag;
    @ApiModelProperty(example = "img.img")
    private String img;
    @ApiModelProperty(example = "10")
    private Integer likes;
    @ApiModelProperty(example = "2023-02-06T23:47:44.082+00:00")
    private Timestamp createTime;
    @ApiModelProperty(example = "2023-02-06T23:47:44.082+00:00")
    private Timestamp updateTime;
    @ApiModelProperty(example = "9")
    private Integer comments;
    @ApiModelProperty(example = "akdgns12")
    private String nickname;
    @ApiModelProperty(example = "1 : 좋아요 o, 0 : 좋아요 x")
    private Integer userLike;
    @ApiModelProperty(example = "[albumComment]")
    private List<AlbumCommentDto> albumCommentDtos;

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
        this.userLike = 1;
    }

    public static class AlbumDtoBuilder implements CommonBuilder<AlbumDto> {
        private Integer id;
        private Integer groupId;
        private String userId;
        private String content;
        private List<TagDto> tag;
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
            this.tag = new ArrayList<>();
            for (AlbumTag albumTag: album.getTag()) {
                TagDto tagDto = new TagDto();
                tagDto.setTagContent(albumTag.getTag());
                this.tag.add(tagDto);
            }
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
