package com.family.gati.dto;

import com.family.gati.entity.AdminMission;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AdminMissionDto {
    private Integer id;
    private String title;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Date startDate;
    private Date endDate;

    private AdminMissionDto(AdminMissionDtoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static class AdminMissionDtoBuilder implements CommonBuilder<AdminMissionDto> {
        private Integer id;
        private String title;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Date startDate;
        private Date endDate;

        public AdminMissionDtoBuilder(AdminMission adminMission) {
            this.id = adminMission.getId();
            this.title = adminMission.getTitle();
            this.createTime = adminMission.getCreateTime();
            this.updateTime = adminMission.getUpdateTime();
            this.startDate = adminMission.getStartDate();
            this.endDate = adminMission.getEndDate();
        }

        public AdminMissionDto build() {
            return new AdminMissionDto(this);
        }
    }
}
