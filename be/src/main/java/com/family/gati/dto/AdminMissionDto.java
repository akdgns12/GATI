package com.family.gati.dto;

import com.family.gati.entity.AdminMission;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AdminMissionDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "미션의 제목 입니다.")
    private String title;
    @ApiModelProperty(example = "미션의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "img.img")
    private String img;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp createTime;
    @ApiModelProperty(example = "2023-02-06 14:31:19")
    private Timestamp updateTime;
    @ApiModelProperty(example = "2023-02-06")
    private Date startDate;
    @ApiModelProperty(example = "2023-02-12")
    private Date endDate;

    private AdminMissionDto(AdminMissionDtoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.img = builder.img;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static class AdminMissionDtoBuilder implements CommonBuilder<AdminMissionDto> {
        private Integer id;
        private String title;
        private String content;
        private String img;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Date startDate;
        private Date endDate;

        public AdminMissionDtoBuilder(AdminMission adminMission) {
            this.id = adminMission.getId();
            this.title = adminMission.getTitle();
            this.content = adminMission.getImg();
            this.img = adminMission.getImg();
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
