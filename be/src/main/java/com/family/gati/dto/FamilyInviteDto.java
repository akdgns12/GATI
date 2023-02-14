package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyInviteDto {
    private String nickName;
    private int familyId;
    private String groupName;
    @ApiModelProperty(value = "초대 받는 사람")
    private String receiverId;
}
