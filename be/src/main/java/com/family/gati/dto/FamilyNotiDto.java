package com.family.gati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FamilyNotiDto {
    private int groupId;
    private String groupName;
    private String senderNickname;
    private String receiverId;
    private int type;
}
