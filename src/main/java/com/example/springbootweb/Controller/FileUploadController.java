package com.example.springbootweb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class FileUploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 文件上传
     * @param file
     * @param req
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest req){
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img")+format;
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.indexOf("."));
        try {
            file.transferTo(new File(folder,newName));
            return req.getScheme() + "://" +req.getServerName() + ":" +req.getServerPort() + "/img"+ format + "/" + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 多文件上传 （文件名相同时用MultipartFile[]，不同时写多个MultipartFile）
     * @param files
     * @param req
     * @return
     */
    @PostMapping("/uploads")
    public String uploads(MultipartFile[] files, HttpServletRequest req){
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/img")+format;
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        for (MultipartFile file : files) {
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.indexOf("."));
            try {
                file.transferTo(new File(folder,newName));
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + "/" + newName;
                log.info(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

}
