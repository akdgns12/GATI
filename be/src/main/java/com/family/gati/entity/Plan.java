package com.family.gati.entity;

import com.family.gati.dto.PlanDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="PLAN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Plan extends BaseTimeEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)//user와 다대일 매핑
    @JoinColumn(name = "USER_GROUP_ID")
    private User user;

    @Column(name = "GROUP_ID")
    @NotNull
    private int groupId;

    @Column(name = "TITLE", length = 50)
    @NotNull
    private String title;

    @Column(name="START_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    @Column(name="END_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date endDate;

    @Column(name="START_TIME")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date startTime;

    @Column(name="END_TIME")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date endTime;

    @Column(name="PLACE", length=50, nullable = true)
    private String place;

    @Column(name="MEMO", length=200, nullable = true)
    private String memo;

    private Plan(PlanBuilder builder){
        this.id = builder.id;
        this.user = builder.user;
        this.groupId = builder.groupId;
        this.title = builder.title;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.place = builder.place;
        this.memo = builder.memo;
    }

    public static class PlanBuilder implements CommonBuilder<Plan>{
        private Integer id;
        private User user;
        private Integer groupId;
        private String title;
        private Date startDate;
        private Date endDate;
        private Date startTime;
        private Date endTime;
        private String place;
        private String memo;

        public PlanBuilder(PlanDto planDto){
            this.id = planDto.getId();
            this.user = planDto.getUser();
            this.groupId = planDto.getGroupId();
            this.title = planDto.getTitle();
            this.startDate = planDto.getStartDate();
            this.endDate = planDto.getEndDate();
            this.startTime = planDto.getStartTime();
            this.endTime = planDto.getEndTime();
            this.place = planDto.getPlace();
            this.memo = planDto.getMemo();
        }
        @Override
        public Plan build(){
            return new Plan(this);
        }
    }
    public void setUser(User user){//user와 연관관계 정의 메소드
        if(this.user!=null){
            this.user.getPlans().remove(this);
        }
        this.user = user;
        if(!user.getPlans().contains(this)){
            user.addPlan(this);
        }
    }
}
