package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentNotiDto {
    
    @ApiModelProperty(value = "댓글 달린 사람(board주인)")
    private String to;

    private String boardId;
    
    @ApiModelProperty(value = "댓글 남긴 사람")
    private String from;

    private int type;
}
