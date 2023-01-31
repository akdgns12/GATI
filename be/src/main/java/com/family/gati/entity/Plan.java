package com.family.gati.entity;

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

    @Column(name="CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime;

    @Column(name="UPDATE_TIME", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Builder
    public Plan(int id, String userId, User user, int groupId, String title, Date startDate, Date endDate, Date startTime, Date endTime, String place, String memo, Date createTime, Date updateTime) {
        this.id = id;
        this.user = user;
        this.groupId = groupId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.memo = memo;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
