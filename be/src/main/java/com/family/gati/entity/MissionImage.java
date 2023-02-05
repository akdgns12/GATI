package com.family.gati.entity;

import com.family.gati.dto.MissionImageDto;
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
public class MissionImage {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "GROUP_ID", nullable = false)
    private Integer groupId;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "IMG", nullable = false, length = 200)
    private String img;

    private MissionImage(MissionImage.MissionImageBuilder builder) {
        this.id = builder.id;
        this.groupId = builder.groupId;
        this.userId = builder.userId;
        this.img = builder.img;
    }

    public static class MissionImageBuilder implements CommonBuilder<MissionImage> {
        private Integer id;
        private Integer groupId;
        private String userId;
        private String img;

        public MissionImageBuilder(MissionImageDto missionImageDto) {
            this.id = missionImageDto.getId();
            this.groupId = missionImageDto.getGroupId();
            this.userId = missionImageDto.getUserId();
            this.img = missionImageDto.getImg();
        }

        public MissionImage build() {return new MissionImage(this);}
    }
}
