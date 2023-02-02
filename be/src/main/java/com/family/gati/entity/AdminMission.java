package com.family.gati.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AdminMission {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "CREATE_TIME", nullable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME", nullable = false)
    private Timestamp updateTime;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;
}
