package com.family.gati.dto;

import com.family.gati.entity.MissionImage;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MissionImageDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer missionId;
    @ApiModelProperty(example = "userId")
    private String userId;
    @ApiModelProperty(example = "img.img")
    private String img;

    private MissionImageDto(MissionImageDtoBuilder builder) {
        this.id = builder.id;
        this.missionId = builder.missionId;
        this.userId = builder.userId;
        this.img = builder.img;
    }

    public static class MissionImageDtoBuilder implements CommonBuilder<MissionImageDto> {
        private Integer id;
        private Integer missionId;
        private String userId;
        private String img;

        public MissionImageDtoBuilder(MissionImage missionImage) {
            this.id = missionImage.getId();
            this.missionId = missionImage.getMissionId();
            this.userId = missionImage.getUserId();
            this.img = missionImage.getImg();
        }

        public MissionImageDto build() {return new MissionImageDto(this);}
    }
}
