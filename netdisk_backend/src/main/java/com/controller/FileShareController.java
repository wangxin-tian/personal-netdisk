package com.controller;

import com.pojo.TbShare;
import com.service.TbShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/share")
public class FileShareController {
    @Autowired
    TbShareService service;

    @PostMapping("/sharefile")
    public boolean shareUserFile(@RequestBody  TbShare share){

        service.insertShareFile(
                share.getShareUser(),
                share.getShareUserfileId(),
                share.getShareComment(),
                share.getFileLocation(),
                share.getShareDate()
        );

        return true;
    }

    @GetMapping ("/findall")
    public List<TbShare> findALLShareFile() {
        // 查询所有分享文件
        return service.findALLShareFile();
    }
}
