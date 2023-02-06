package com.family.gati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanSignUpDto {
    private String userId;
    private int groupId;
    private String title;
    private String startDate;
    private String endDate;
    private String place;
    private String memo;
}
