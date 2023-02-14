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
public class BoardRegistDto {
    @ApiModelProperty(example = "1")
    private Integer groupId;
    @ApiModelProperty(example = "userid")
    private String userId;
    @ApiModelProperty(example = "게시글의 내용 입니다.")
    private String content;
    @ApiModelProperty(example = "[{'tagContent':'부산'}, {'tagContent':'울산'}]")
    private List<TagDto> tagDtos;
}
