package com.family.gati.dto;

import com.family.gati.entity.MissionImage;
import com.family.gati.util.CommonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissionImageDto {
    private Integer id;
    private Integer groupId;
    private String userId;
    private String img;

    private MissionImageDto(MissionImageDtoBuilder builder) {
        this.id = builder.id;
        this.groupId = builder.groupId;
        this.userId = builder.userId;
        this.img = builder.img;
    }

    public static class MissionImageDtoBuilder implements CommonBuilder<MissionImageDto> {
        private Integer id;
        private Integer groupId;
        private String userId;
        private String img;

        public MissionImageDtoBuilder(MissionImage missionImage) {
            this.id = missionImage.getId();
            this.groupId = missionImage.getGroupId();
            this.userId = missionImage.getUserId();
            this.img = missionImage.getImg();
        }

        public MissionImageDto build() {return new MissionImageDto(this);}
    }
}
