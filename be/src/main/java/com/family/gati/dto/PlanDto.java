package com.family.gati.dto;

import com.family.gati.entity.Plan;
import com.family.gati.entity.User;
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
        private Integer id;
        private User user;
        private Integer groupId;
        private String title;
        private Date startDate;
        private Date endDate;
        private String place;
        private String memo;
        private Date createTime;
        private Date updateTime;

        public PlanDtoBuilder(Plan plan){
            this.id = plan.getId();
            this.user = plan.getUser();
            this.groupId = plan.getGroupId();
            this.title = plan.getTitle();
            this.startDate = plan.getStartDate();
            this.endDate = plan.getEndDate();
            this.place = plan.getPlace();
            this.memo = plan.getMemo();
            this.createTime = plan.getCreateTime();
            this.updateTime = plan.getUpdateTime();
        }
        @Override
        public PlanDto build(){
            return new PlanDto(this);
        }
    }
}
