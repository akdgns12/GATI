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
public class AlbumUpdateDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "앨범의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "@서울")
    private String tag;
    @ApiModelProperty(example = "img.img")
    private String img;
}
