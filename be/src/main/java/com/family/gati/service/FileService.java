package com.family.gati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${upload.dir}")
    private String uploadDir;
    public String fileUpload(MultipartFile multipartFile, String location) throws Exception{
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");

//        ubuntu only
        String filePath = uploadDir+"/"+location+File.separator+date.format(dateTimeFormatter)+"_"+multipartFile.getOriginalFilename();
        System.out.println(filePath);
        String fileDir = uploadDir+"/"+location;
        //        window only
//        String filePath = uploadDir+"\\"+location+"\\"+date.format(dateTimeFormatter)+"_"+multipartFile.getOriginalFilename();
//        String fileDir = uploadDir+"\\"+location;
        File file = new File(fileDir);
        if(!file.exists()){
            file.mkdir();
            Path path = Paths.get(filePath);
            multipartFile.transferTo(path);
        }else{
            Path path = Paths.get(filePath);
            multipartFile.transferTo(path);
        }
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
