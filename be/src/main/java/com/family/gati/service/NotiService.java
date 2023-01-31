package com.family.gati.service;

import com.family.gati.dto.NotiDto;
import com.family.gati.entity.Noti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NotiService {

    List<NotiDto> findByUserId(String userId);
}
