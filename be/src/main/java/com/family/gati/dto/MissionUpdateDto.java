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
public class MissionUpdateDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "4")
    private Integer memNumber;
    @ApiModelProperty(example = "1")
    private Integer groupId;
}
