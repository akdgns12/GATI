package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyInviteDto {
    private int id;
    private String name;
    @ApiModelProperty(value = "초대 받는 사람")
    private String to;
    private int type;
}
