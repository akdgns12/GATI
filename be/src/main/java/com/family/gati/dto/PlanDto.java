package com.family.gati.dto;

import com.family.gati.entity.Plan;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PlanDto {
    private Integer id;
    private String userId;
    private Integer groupId;
    private String title;
    private Date startDate;
    private Date endDate;
    private String place;
    private String memo;
    private Date createTime;
    private Date updateTime;
    List<Plan> plans = new ArrayList<>();

    private PlanDto(PlanDto.PlanDtoBuilder builder){
        this.id = builder.id;
    }

    public static class PlanDtoBuilder implements CommonBuilder<PlanDto> {

    }
}
