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
public class BoardRegistDto {
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "게시글의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "@부산")
    private String tag;
    @ApiModelProperty(example = "img.img")
    private String img;
}
