package com.family.gati.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FamilyMemberDto {
    private int id;
    private String userId;
    private String familyId;
}
