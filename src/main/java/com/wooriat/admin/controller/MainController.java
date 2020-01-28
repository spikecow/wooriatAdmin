package com.wooriat.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final String MAIN_RETURN_URL = "main/main";

    private final ServletContext servletContext;


    @GetMapping("/")
    @ResponseBody
    public ModelAndView mainController(@RequestParam Map<String, Object> params, Model model){
        log.info("mainController! - Start");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MAIN_RETURN_URL);

        log.info("mainController! - End");
        return modelAndView;
    }

    private String getAwsUploadFile(MultipartHttpServletRequest req,String dir,String tagName,String fileType,String fileName){

        String returnUrl = "";
        try {
            Part part = req.getPart(tagName);

            if (part != null && part.getSize() > 0) {
                String uploadPath = File.separator + "uploadsTemp";
                File uploadDir = new File(servletContext.getRealPath("/") + uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String uploadedFileName = part.getSubmittedFileName();
                String uploadedFileExt = uploadedFileName.substring(uploadedFileName.lastIndexOf("."));
                LocalDateTime localDate = LocalDateTime.now();
                String uploadTime = localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                String replacedFileName = fileName.replaceAll(" ","_");
                String uploadedFilePath = uploadDir.getAbsolutePath() + File.separator + uploadTime+"_"+replacedFileName+uploadedFileExt;

                part.write(uploadedFilePath);

                File tempFile = new File(uploadedFilePath);

                String uploadFileUrl = "static/"+dir+"/"+fileType+"/"+tempFile.getName();

                //returnUrl = amazonS3Client.uploadAWSS3File(uploadFileUrl, tempFile);

                if(tempFile.exists()){
                    tempFile.delete();
                }
            }

        }catch(Exception ex){
            log.error("AWS S3 Exception : {}",ex);
        }

        return returnUrl;
    }

}
