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
    @ApiModelProperty(example = "닉네임")
    private String nickName;
    private MissionImageDto(MissionImageDtoBuilder builder) {
        this.id = builder.id;
        this.missionId = builder.missionId;
        this.userId = builder.userId;
        this.img = builder.img;
        this.nickName = builder.nickName;
    }

    public static class MissionImageDtoBuilder implements CommonBuilder<MissionImageDto> {
        private Integer id;
        private Integer missionId;
        private String userId;
        private String img;
        private String nickName;

        public MissionImageDtoBuilder(MissionImage missionImage) {
            this.id = missionImage.getId();
            this.missionId = missionImage.getMissionId();
            this.userId = missionImage.getUserId();
            this.img = missionImage.getImg();
            this.nickName = missionImage.getNickName();
        }

        public MissionImageDto build() {return new MissionImageDto(this);}
    }
}
