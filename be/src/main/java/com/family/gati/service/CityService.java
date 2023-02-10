package com.family.gati.service;

import com.family.gati.dto.CityDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

public interface CityService {
    List<CityDto> findAllOrderByTagCntLimit10();
    List<CityDto> apiResponseData() throws IOException;


}