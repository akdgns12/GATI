package com.family.gati.api;

import com.family.gati.dto.BoardDto;
import com.family.gati.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
@Api(tags = "City API")
public class CityApiController {
    private final CityService cityService;

    @ApiOperation(
            value = "현재 그룹의 Board page 조회"
            , notes = "GroupId와 page 번호(0부터 시작)를 통해 현재 그룹의 Board page를 최신순으로 12개 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "조회 성공"
                    , response = BoardDto.class
                    , responseContainer = "List"
            )
    })
}
