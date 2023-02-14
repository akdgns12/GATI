package com.family.gati.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class DeleteFamilyDto {
    private int familyId;
    private String userId;
}
