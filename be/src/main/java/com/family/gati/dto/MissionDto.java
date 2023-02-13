package com.family.gati.dto;

import com.family.gati.entity.Mission;
import com.family.gati.entity.MissionImage;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MissionDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "미션의 제목 입니다.")
    private String title;
    @ApiModelProperty(example = "미션의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "img.img")
    private String img;
    @ApiModelProperty(example = "4")
    private Integer memNumber;
    @ApiModelProperty(example = "1")
    private Integer adminMissionId;
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "0 : 인원 설정 이전, 1 : 미완료, 2 : 완료")
    private Integer completed;
    @ApiModelProperty(example = "['MissionImageDto', 'MissionImageDto']")
    private List<MissionImageDto> missionImageDtos;
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

//    public void setMissionImages(List<MissionImage> missionImages) {
//        for (MissionImage missionImage: missionImages) {
//            missionImageDtos.add(new MissionImageDto.MissionImageDtoBuilder(missionImage).build());
//        }
//    }

    private MissionDto(MissionDto.MissionDtoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.img = builder.img;
        this.memNumber = builder.memNumber;
        this.adminMissionId = builder.adminMissionId;
        this.groupId = builder.groupId;
        this.completed = builder.completed;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static class MissionDtoBuilder implements CommonBuilder<MissionDto> {
        private Integer id;
        private String title;
        private String content;
        private String img;
        private Integer memNumber;
        private Integer adminMissionId;
        private Integer groupId;
        private Integer completed;
        private Date startDate;
        private Date endDate;


        public MissionDtoBuilder(Mission mission) {
            this.id = mission.getId();
            this.title = mission.getTitle();
            this.content = mission.getContent();
            this.img = mission.getImg();
            this.memNumber = mission.getMemNumber();
            this.adminMissionId = mission.getAdminMissionId();
            this.groupId = mission.getGroupId();
            this.completed = mission.getCompleted();
            this.startDate = mission.getStartDate();
            this.endDate = mission.getEndDate();
        }

        public MissionDto build() {
            return new MissionDto(this);
        }

    }
}
