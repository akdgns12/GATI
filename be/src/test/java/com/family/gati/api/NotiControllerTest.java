package com.family.gati.api;

import com.family.gati.dto.NotiDto;
import com.family.gati.service.NotiService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NotiControllerTest {
    String userId="hhj";
    NotiDto notiDto = new NotiDto();
    NotiService notiService;
    @Test
    public void getNotiByUserId() throws Exception{
        notiDto.setId(1);
        notiDto.setNickname("polar");
        notiDto.setType(2);
        notiDto.setUserId("hhj");
        notiDto.setGroupId(3);
        notiDto.setGroupName("family");
        userId = "hhj";

        List<NotiDto> result = notiService.getByUserId(userId);

        assertTrue(userId.equals(result.get(0).getUserId()));
    }
}