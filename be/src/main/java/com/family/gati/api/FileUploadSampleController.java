package com.family.gati.api;

import com.family.gati.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/fileUpload")
@Api(tags = "File Upload API")
public class FileUploadSampleController {
    private final FileService fileService;
    @ApiOperation(value = "file upload")
    @PostMapping("/list")
    public ResponseEntity<?> getAdminMissions(MultipartFile file) throws Exception{
        // 저장될 폴더 이름인데, 관련 entity 명으로 하면 좋을 듯 합니다.
        // test전 C드라이브/SSAFY/media 만들어 둘 것.
        // .../{location}/filename.jpg
        String location = "sample";

        // 저장된 주소로 db에 저장할 것.
        String path = fileService.fileUpload(file, location);
        return ResponseEntity.ok(path);
    }

}
