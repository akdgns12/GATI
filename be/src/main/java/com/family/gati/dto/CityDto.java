package com.family.gati.dto;

import com.family.gati.entity.City;
import com.family.gati.util.CommonBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "도시")
    private String tag;
    @ApiModelProperty(example = "1")
    private Integer tagId;
    @ApiModelProperty(example = "9")
    private Integer tagCnt;

    private CityDto(CityDtoBuilder builder){
        this.id = builder.id;
        this.tag = builder.tag;
        this.tagCnt = builder.tagCnt;
        this.tagId = builder.tagId;
    }

    public static class CityDtoBuilder implements CommonBuilder<CityDto>{
        private Integer id;
        private Integer tagId;
        private String tag;
        private Integer tagCnt;

        public CityDtoBuilder(City city){
            this.id = city.getId();
            this.tag = city.getTag();
            this.tagCnt = city.getTagCnt();
        }
        public CityDto build(){
            return new CityDto(this);
        }
    }

}
