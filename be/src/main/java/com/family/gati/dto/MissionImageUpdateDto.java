package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissionImageUpdateDto {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "img.img")
    private String img;

}
