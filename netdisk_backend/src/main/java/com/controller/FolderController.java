package com.controller;

import com.pojo.TbFolder;
import com.service.TbFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    private TbFolderService service;

    @PostMapping ("/allfolders")
    public List<TbFolder> findallfolders(@RequestBody TbFolder folder){
        // System.out.println("--------" + belongUser + "--------");
//        belongUser = belongUser.toString().replace("=","");
        // System.out.println("--------" + belongUser + "--------");
        // return service.findAllFolders(belongUser);
        return service.findAllFolders(folder.getFolderFather(), folder.getBelongUser(), folder.getIsdel());

    }

    // 垃圾箱的文件夹
    @PostMapping ("/rufolders")
    public List<TbFolder> findRubbishFolders(@RequestBody TbFolder folder){
        // System.out.println("--------" + belongUser + "--------");
//        belongUser = belongUser.toString().replace("=","");
        // System.out.println("--------" + belongUser + "--------");
        // return service.findAllFolders(belongUser);
        return service.findFolders(folder.getBelongUser(), folder.getIsdel());

    }

    // 垃圾箱的文件夹
    @PostMapping ("/updatefolder")
    public boolean updateFolderState(@RequestBody TbFolder folder){

        service.updateFolder(folder.getFolderId(), folder.getIsdel());

        return true;
    }

    @PostMapping ("/deletefolder")
    public boolean deleteFolder(@RequestBody TbFolder folder){

        service.deleteFolder(folder.getFolderId());

        return true;
    }

    @PostMapping ("/searchfolder")
    public List<TbFolder> findByFolderName(@RequestBody TbFolder folder){
        // 根据文件夹名称查询文件夹
        return service.findByFolderName(folder.getBelongUser(),
                folder.getFolderName(), folder.getIsdel());

    }

    @PostMapping("/newfolder")
    public boolean insertUserFolder(@RequestBody TbFolder folder){
        // 插入一个新的文件夹
        service.insertUserFolder(folder.getFolderName(),
                folder.getFolderFather(), folder.getBelongUser(),
                folder.getFolderCreatetime(), folder.getIsdel());
        return true;
    }
}
