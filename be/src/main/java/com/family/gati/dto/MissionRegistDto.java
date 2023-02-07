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
public class MissionRegistDto {
    @ApiModelProperty(example = "img.img")
    private String img;
    @ApiModelProperty(example = "4")
    private Integer memNumber;
    @ApiModelProperty(example = "1")
    private Integer groupId;
}
