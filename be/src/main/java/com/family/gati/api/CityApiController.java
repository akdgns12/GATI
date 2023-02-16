package com.family.gati.api;

import com.family.gati.dto.CityDto;
import com.family.gati.dto.RecommandDto;
import com.family.gati.service.CityService;
import com.family.gati.service.RecommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> pushDb(@RequestParam String password) throws Exception {
        if (!password.equals("gudwns"))
            return ResponseEntity.ok(null);
        recommandService.apiResponseData();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/pushcitydb")
    public ResponseEntity<?> pushCityDb(@RequestParam String password) throws Exception {
        if (!password.equals("gudwns"))
            return ResponseEntity.ok(null);
        cityService.pushCityDb();
        return ResponseEntity.ok(null);
    }

}
