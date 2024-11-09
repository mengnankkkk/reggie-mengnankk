package com.mengnankk.controller;

import com.mengnankk.comon.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info(file.toString());

        String orginname = file.getOriginalFilename();//原始文件名
        String suffix = orginname.substring(orginname.lastIndexOf("."));
        String uuidname = UUID.randomUUID().toString()+suffix;

        File dir  = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+uuidname));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(uuidname);
    }


    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
             ServletOutputStream servletOutputStream = response.getOutputStream()) {

            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            int len;

            while ((len = fileInputStream.read(bytes)) != -1) {
                servletOutputStream.write(bytes, 0, len);
            }

            servletOutputStream.flush();
        } catch (FileNotFoundException e) {
            // 文件未找到的处理逻辑
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            log.error("File not found: " + name, e);
        } catch (IOException e) {
            // 其他I/O异常处理
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error("Error occurred during file download", e);
        }
        finally {
            log.info("开始下载");
        }
    }

}