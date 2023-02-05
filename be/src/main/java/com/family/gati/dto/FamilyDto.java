package com.family.gati.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FamilyDto {

    private int id;
    private String name;
    private int familyMember;
    private String img;
    private String masterId;
}
