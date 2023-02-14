package com.family.gati.entity;

import com.family.gati.dto.MissionImageDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "MISSION_IMAGE")
public class MissionImage {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "MISSION_ID", nullable = false)
    private Integer missionId;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "IMG", nullable = false, length = 500)
    private String img;

    private MissionImage(MissionImage.MissionImageBuilder builder) {
        this.id = builder.id;
        this.missionId = builder.missionId;
        this.userId = builder.userId;
        this.img = builder.img;
    }

    public static class MissionImageBuilder implements CommonBuilder<MissionImage> {
        private Integer id;
        private Integer missionId;
        private String userId;
        private String img;

        public MissionImageBuilder(MissionImageDto missionImageDto) {
            this.id = missionImageDto.getId();
            this.missionId = missionImageDto.getMissionId();
            this.userId = missionImageDto.getUserId();
            this.img = missionImageDto.getImg();
        }

        public MissionImage build() {return new MissionImage(this);}
    }
}
