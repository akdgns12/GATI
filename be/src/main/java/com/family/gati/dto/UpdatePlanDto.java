package com.family.gati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePlanDto {
    private int id;
    private String userId;
    private String title;
    private String startDate;
    private String endDate;
    private String place;
    private String memo;
}
