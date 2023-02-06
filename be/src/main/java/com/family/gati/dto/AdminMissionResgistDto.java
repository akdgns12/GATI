package com.family.gati.dto;

import com.family.gati.entity.AdminMission;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AdminMissionResgistDto {
    @ApiModelProperty(example = "미션의 제목 입니다.")
    private String title;
    @ApiModelProperty(example = "2023-02-06")
    private Date startDate;
    @ApiModelProperty(example = "2023-02-12")
    private Date endDate;

}
