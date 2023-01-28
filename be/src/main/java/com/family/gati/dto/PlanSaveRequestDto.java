package com.family.gati.dto;

import com.family.gati.entity.Plan;
import com.family.gati.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class PlanSaveRequestDto {
    private int id;
    private String userId;
    private int groupId;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private String place;
    private String memo;
    private Date createTime;
    private Date updateTime;

    public Plan toEntity(User user){
        Plan plan = Plan.builder()
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .title(title)
                .memo(memo)
                .build();
        plan.setUser(user);
        return plan;
    }
}
