package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeNotiDto {

    @ApiModelProperty(value = "좋아요 받은 사람(board주인)")
    private String receiverId;

    private int boardId;

    @ApiModelProperty(value = "좋아요 누른 사람")
    private String senderNickname;

    private int type;
}