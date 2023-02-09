package com.family.gati.entity;

import com.family.gati.dto.MissionDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "MISSION")
public class Mission {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

    @Column(name = "IMG", nullable = false, length = 500)
    private String img;

    @Column(name = "MEM_NUMBER", nullable = false)
    private Integer memNumber;

    @Column(name = "ADMIN_MISSION_ID", nullable = false)
    private Integer adminMissionId;

    @Column(name = "GROUP_ID", nullable = false)
    private Integer groupId;

    @Column(name = "COMPLETED", nullable = false)
    private Integer completed;

    public Mission(AdminMission adminMission, Integer groupId) {
        this.title = adminMission.getTitle();
        this.content = adminMission.getContent();
        this.img = adminMission.getImg();
        this.memNumber = 0;
        this.adminMissionId = adminMission.getId();
        this.groupId = groupId;
        this.completed = 0;
    }

    private Mission(MissionBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.img = builder.img;
        this.memNumber = builder.memNumber;
        this.adminMissionId = builder.adminMissionId;
        this.groupId = builder.groupId;
        this.completed = builder.completed;
    }

    public static class MissionBuilder implements CommonBuilder<Mission> {
        private Integer id;
        private String title;
        private String content;
        private String img;
        private Integer memNumber;
        private Integer adminMissionId;
        private Integer groupId;
        private Integer completed;

        public MissionBuilder(MissionDto missionDto) {
            this.id = missionDto.getId();
            this.title = missionDto.getTitle();
            this.content = missionDto.getContent();
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
