package com.family.gati.entity;

import com.family.gati.dto.MissionDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mission {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "IMG", nullable = false, length = 200)
    private String img;

    @Column(name = "MEM_NUMBER", nullable = false)
    private Integer memNumber;

    @Column(name = "ADMIN_MISSION_ID", nullable = false)
    private Integer adminMissionId;

    @Column(name = "GROUP_ID", nullable = false)
    private Integer groupId;

    @Column(name = "COMPLETED", nullable = false)
    private Integer completed;

    private Mission(MissionBuilder builder) {
        this.id = builder.id;
        this.img = builder.img;
        this.memNumber = builder.memNumber;
        this.adminMissionId = builder.adminMissionId;
        this.groupId = builder.groupId;
        this.completed = builder.completed;
    }

    public static class MissionBuilder implements CommonBuilder<Mission> {
        private Integer id;
        private String img;
        private Integer memNumber;
        private Integer adminMissionId;
        private Integer groupId;
        private Integer completed;

        public MissionBuilder(MissionDto missionDto) {
            this.id = missionDto.getId();
            this.img = missionDto.getImg();
            this.memNumber = missionDto.getMemNumber();
            this.adminMissionId = missionDto.getAdminMissionId();
            this.groupId = missionDto.getGroupId();
            this.completed = missionDto.getCompleted();
        }

        public Mission build() {
            return new Mission(this);
        }

    }
}
