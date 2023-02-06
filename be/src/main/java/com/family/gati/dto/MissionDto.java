package com.family.gati.dto;

import com.family.gati.entity.Mission;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissionDto {
    private Integer id;
    private String img;
    private Integer memNumber;
    private Integer adminMissionId;
    private Integer groupId;
    private Integer completed;

    private MissionDto(MissionDto.MissionDtoBuilder builder) {
        this.id = builder.id;
        this.img = builder.img;
        this.memNumber = builder.memNumber;
        this.adminMissionId = builder.adminMissionId;
        this.groupId = builder.groupId;
        this.completed = builder.completed;
    }

    public static class MissionDtoBuilder implements CommonBuilder<MissionDto> {
        private Integer id;
        private String img;
        private Integer memNumber;
        private Integer adminMissionId;
        private Integer groupId;
        private Integer completed;

        public MissionDtoBuilder(Mission mission) {
            this.id = mission.getId();
            this.img = mission.getImg();
            this.memNumber = mission.getMemNumber();
            this.adminMissionId = mission.getAdminMissionId();
            this.groupId = mission.getGroupId();
            this.completed = mission.getCompleted();
        }

        public MissionDto build() {
            return new MissionDto(this);
        }

    }
}
