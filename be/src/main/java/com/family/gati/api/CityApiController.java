package com.family.gati.api;

import com.family.gati.dto.CityDto;
import com.family.gati.dto.RecommandDto;
import com.family.gati.service.CityService;
import com.family.gati.service.RecommandService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
@Api(tags = "City API")
public class CityApiController {
    private final CityService cityService;
    private final RecommandService recommandService;

    @GetMapping("/tag")
    public ResponseEntity<?> findAllOrderByTagCnt(){
        List<CityDto> dtos = cityService.findAllOrderByTagCnt();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/pushdb")
    public ResponseEntity<?> pushDb() throws Exception {
        recommandService.apiResponseData();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/pushcitydb")
    public ResponseEntity<?> pushCityDb() throws Exception {
        cityService.pushCityDb();
        return ResponseEntity.ok(null);
    }

}
