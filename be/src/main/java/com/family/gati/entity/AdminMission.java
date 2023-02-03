package com.family.gati.entity;

import com.family.gati.dto.AdminMissionDto;
import com.family.gati.util.CommonBuilder;
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

    private AdminMission(AdminMissionBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static class AdminMissionBuilder implements CommonBuilder<AdminMission> {
        private Integer id;
        private String title;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Date startDate;
        private Date endDate;

        public AdminMissionBuilder(AdminMissionDto adminMissionDto) {
            this.id = adminMissionDto.getId();
            this.title = adminMissionDto.getTitle();
            this.createTime = adminMissionDto.getCreateTime();
            this.updateTime = adminMissionDto.getUpdateTime();
            this.startDate = adminMissionDto.getStartDate();
            this.endDate = adminMissionDto.getEndDate();
        }

        public AdminMission build() {
            return new AdminMission(this);
        }
    }
}
