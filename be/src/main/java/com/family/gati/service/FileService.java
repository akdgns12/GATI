package com.family.gati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${upload.dir}")
    private String uploadDir;
    public String fileUpload(MultipartFile multipartFile, String location) throws Exception{
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");

        //        ubuntu only
        //        String filePath = uploadDir+"/"+location+multipartFile.getOriginalFilename();
        //        window only
        String filePath = uploadDir+"\\"+location+"\\"+date.format(dateTimeFormatter)+"_"+multipartFile.getOriginalFilename();
        String fileDir = uploadDir+"\\"+location;
        File file = new File(fileDir);
        if(!file.exists()){
            file.mkdir();
            file = new File(filePath);
        }else{
            file = new File(filePath);
        }
        multipartFile.transferTo(file);
        return filePath;
    }

    public boolean fileDelete(String path) throws Exception{

        File file = new File(path);
        if(!file.exists()){
            return false;
        }else{
            file.delete();
            return true;
        }
    }

}
