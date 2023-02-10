package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyNotiDto {
    private int id;

    private String name;

    @ApiModelProperty(value = "초대 받는 사람")
    private String to;

    @ApiModelProperty(value = "초대 보내는 사람(master)")
    private String from;

    private int type;
}
