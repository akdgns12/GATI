package com.family.gati.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN")
@Builder
@Getter @Setter
@AllArgsConstructor
public class Plan {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_ID")
    @NotNull
    private String userId;

    @Column(name = "GROUP_ID")
    @NotNull
    private String groupId;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @Column(name = "START_DATE")
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "MEMO")
    private String memo;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Builder
    public Plan(int id, String userId,
                String groupId,
                String title,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String memo,
                LocalDateTime createTime,
                LocalDateTime updateTitme) {
            this.id = id;
            this.userId = userId;
            this.groupId = groupId;
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
            this.memo = memo;
            this.createTime = createTime;
            this.updateTime = updateTitme;
    }

    public Plan() {

    }
}
