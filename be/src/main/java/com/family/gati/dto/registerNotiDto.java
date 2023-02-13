package com.family.gati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class registerNotiDto {
    private int groupId;
    private String masterId;
    private String userId;
    private String groupName;
    private int type;
}
