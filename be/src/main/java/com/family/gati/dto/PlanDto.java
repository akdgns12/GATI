package com.family.gati.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PlanDto {
    private int id;
    private String userId;
    private int groupId;
    private String title;
    private String startDate;
    private String endDate;
    private String place;
    private String memo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
