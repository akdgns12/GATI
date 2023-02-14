package com.family.gati.dto;

import com.family.gati.entity.MissionImage;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter @Getter
public class MissionImageRegistDto {
    @ApiModelProperty(example = "1")
    private Integer missionId;
    @ApiModelProperty(example = "userId")
    private String userId;
}
