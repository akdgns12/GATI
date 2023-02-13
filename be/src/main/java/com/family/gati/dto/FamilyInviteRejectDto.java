package com.family.gati.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class FamilyInviteRejectDto {
    private String userId;
    private int familyId;
}
