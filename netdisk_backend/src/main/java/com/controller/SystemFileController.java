package com.controller;

import com.pojo.TbSystemFile;
import com.service.TbSystemFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/system")
public class SystemFileController {
    @Autowired
    private TbSystemFileService service;

    @PostMapping("upsfile")
    private boolean insertSystemFile(@RequestBody TbSystemFile file) {

        service.insertSystemFile(file.getFileName(), file.getFileUrl(), file.getBelongUser(),
                file.getFileType(), file.getFileSize(), file.getUploadTime());

        return true;
    }

}
