package com.family.gati.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GroupDto {

    private int id;
    private String name;
    private int groupMember;
    private String img;
    private String masterId;
}
