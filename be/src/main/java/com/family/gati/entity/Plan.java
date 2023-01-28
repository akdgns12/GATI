package com.family.gati.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="PLAN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Plan extends BaseTimeEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;

    @Column(name = "USER_ID", length = 20, unique = true)
    @NotNull
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)//user와 다대일 매핑
    @JoinColumn(name = "USER_ID")
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
    private LocalDate startDate;

    @Column(name="END_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate endDate;

    @Column(name="START_TIME")
    @Temporal(TemporalType.TIME)
    @NotNull
    private LocalTime startTime;

    @Column(name="END_TIME")
    @Temporal(TemporalType.TIME)
    @NotNull
    private LocalTime endTime;

    @Column(name="PLACE", length=50, nullable = true)
    private String place;

    @Column(name="MEMO", length=200, nullable = true)
    private String memo;

    @Column(name="CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime;

    @Column(name="UPDATE_TIME", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

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
