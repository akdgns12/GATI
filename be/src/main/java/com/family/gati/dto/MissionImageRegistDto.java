package com.family.gati.dto;

import com.family.gati.entity.MissionImage;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissionImageRegistDto {
    @ApiModelProperty(example = "1")
    private Integer missionId;
    @ApiModelProperty(example = "userId")
    private String userId;
    @ApiModelProperty(example = "img.img")
    private String img;

}
