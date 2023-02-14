package com.family.gati.dto;

import com.family.gati.entity.Board;
import com.family.gati.entity.BoardTag;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "userid")
    private String userId;
    @ApiModelProperty(example = "게시글의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "['부산', '울산']")
    private List<TagDto> tag;
    @ApiModelProperty(example = "img.img")
    private String img;
    @ApiModelProperty(example = "10")
    private Integer likes;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp createTime;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp updateTime;
    @ApiModelProperty(example = "9")
    private Integer comments;
    @ApiModelProperty(example = "akdgns12")
    private String nickname;
    @ApiModelProperty(example = "1 : 좋아요 o, 0 : 좋아요 x")
    private Integer userLike;
    @ApiModelProperty(example = "[boardComment]")
    private List<BoardCommentDto> boardCommentDtos;

    private BoardDto(BoardDtoBuilder builder) {
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

    public static class BoardDtoBuilder implements CommonBuilder<BoardDto> {
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


        public BoardDtoBuilder(Board board) {
            this.id = board.getId();
            this.groupId = board.getGroupId();
            this.userId = board.getUserId();
            this.content = board.getContent();
            this.tag = new ArrayList<>();
            for (BoardTag boardTag: board.getTag()) {
                TagDto tagDto = new TagDto();
                tagDto.setTagContent(boardTag.getTag());
                this.tag.add(tagDto);
            }
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
