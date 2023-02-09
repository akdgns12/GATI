package com.family.gati.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Getter
@Setter
public class MissionRegistDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "4")
    private Integer memNumber;
}
