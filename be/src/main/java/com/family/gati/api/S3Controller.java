package com.family.gati.api;

import com.family.gati.dto.FileDto;
import com.family.gati.entity.FileEntity;
import com.family.gati.service.FileService;
import com.family.gati.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;
    private final FileService fileService;

    @GetMapping("api/upload")
    public String goToUpload(){
        return "upload";
    }

    @PostMapping("api/upload")
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile fileUp,  String title) throws IOException{
        FileDto fileDto = new FileDto();
        fileDto.setFile(fileUp);
        fileDto.setTitle(title);

        String url = s3Service.uploadFile(fileDto.getFile());

        fileDto.setUrl(url);
        fileService.save(fileDto);

        return "redirect:api/list1";
    }

    @GetMapping("api/list")
    public ResponseEntity<?> listPage(Model model){
        List<FileEntity> fileList = fileService.getFiles();
        model.addAttribute("fileList", fileList);
        return ResponseEntity.ok(model);
    }
}
