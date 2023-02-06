package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AdminMissionUpdateDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "미션의 제목 입니다.")
    private String title;
    @ApiModelProperty(example = "2023-02-06")
    private Date startDate;
    @ApiModelProperty(example = "2023-02-12")
    private Date endDate;

}
