package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AlbumUpdateDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "앨범의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "[{'tagContent':'부산'}, {'tagContent':'울산'}]")
    private List<TagDto> tagDtos;
}
