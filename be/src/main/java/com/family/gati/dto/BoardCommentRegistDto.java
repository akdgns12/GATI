package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BoardCommentRegistDto {
    @ApiModelProperty(example = "1")
    private Integer boardId;
    @ApiModelProperty(example = "댓글의 내용 입니다.")
    private String content;
}
