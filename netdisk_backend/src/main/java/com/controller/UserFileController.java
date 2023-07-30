package com.controller;

import com.pojo.*;
import com.service.TbSystemFileService;
import com.service.TbUserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/file")
public class UserFileController {

    private static final int NUM_NODES = 6; // 存储节点数
    private static final String[] NODES = {"node1", "node2", "node3", "node4", "node5", "node6"}; // 存储节点名称

    public static String getNode(String fileName) {
        int hash = fileName.hashCode(); // 计算哈希值
        int nodeIndex = Math.abs(hash % NUM_NODES); // 取模得到存储节点编号

        System.out.println(fileName + "\n" + hash + "\n" + nodeIndex + "\n" + NODES[nodeIndex] + "\n\n");

        return NODES[nodeIndex]; // 返回存储节点名称
    }

    @Autowired
    TbUserFileService service;
//    private TbSystemFileService tbSystemFileService;

    String path = "D:\\Repositories\\netdisk\\netdisk_backend\\src\\main\\resources\\upload";

    @PostMapping ("/userfile")
    public List<TbUserFile> findUserFile(@RequestBody TbUserFile file){
        return service.findFiles(file.getBelongUser(), file.getFileLocation(), file.getIsDel());
    }

    @PostMapping ("/rufiles")
    public List<TbUserFile> findRubbishFiles(@RequestBody TbUserFile file){
        return service.findAllFiles(file.getBelongUser(), file.getIsDel());
    }

    @PostMapping("upufile")
    private boolean insertUserFile(@RequestBody TbUserFile file) {
        System.out.println("==========================看这里=======================");
        System.out.println(file);
//        Integer sysId = tbSystemFileService.
        service.insertUserFile(null, file.getFileName(), file.getBelongUser(),
                file.getFileType(), file.getFileSize() / 1024, file.getFileLocation(),
                file.getUploadTime(), 0);

        return true;
    }

    @PostMapping("/upload")
    public String nativeUpload(@RequestParam("file") MultipartFile file){

        // 步骤1.首先实现上传
        String originalFilename = file.getOriginalFilename();//获取文件原始名称
        String nodeName = getNode(originalFilename);
        // 是Java Servlet中获取Web应用程序的实际路径，并在路径后面添加“/upload”来表示一个名为“upload”的目录。
        // String path = request.getSession().getServletContext().getRealPath("").toString()+"/upload/";

//        String fileurl = path + "\\" +originalFilename;
        String fileurl = path + "\\" + nodeName + "\\" +originalFilename;
        File localfile = new File(fileurl);
        if(!localfile.getParentFile().exists()){ //如果文件夹不存会直接创建。
            localfile.mkdirs();
        }

        // 判断文件是否存在，如果存在则先删除
        if (localfile.exists()) {
            localfile.delete();
        }

        try{
            file.transferTo(localfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/pictures")
    public List<TbUserFile> findPictures(@RequestBody String belongUser){
        belongUser = belongUser.toString().replace("=","");

        return service.findAllPictures(Integer.parseInt(belongUser), 1);
    }

    @PostMapping("/video")
    public List<TbUserFile> findvideo(@RequestBody String belongUser){
        belongUser = belongUser.toString().replace("=","");

        return service.findAllPictures(Integer.parseInt(belongUser), 3);
    }

    @PostMapping("/audio")
    public List<TbUserFile> findaudio(@RequestBody String belongUser){
        belongUser = belongUser.toString().replace("=","");

        return service.findAllPictures(Integer.parseInt(belongUser), 2);
    }

    @PostMapping("/preview")
    public byte[] previewPicture(@RequestBody TbUserFile filescale) throws IOException{
        String nodeName = getNode(filescale.getFileName());
        // 获取文件
        String filepath = path + "\\" + nodeName + "\\" + filescale.getFileName();
//        File file = new File(filepath);
//        FileInputStream fis = new FileInputStream(filepath);

        // 读取文件字节数组
//        byte[] bytes = new byte[(int) file.length()];
//        byte[] bytes = new byte[(int) fis.getChannel().size()];
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
//        fis.read(bytes);
//        fis.close();
        return bytes;
    }

    @PostMapping("/audition")
    public byte[] auditionMusic(@RequestBody TbUserFile filescale) throws IOException{
        // 获取文件
        String nodeName = getNode(filescale.getFileName());
        String filepath = path + "\\" + nodeName + "\\" + filescale.getFileName();
//        File file = new File(filepath);
//        FileInputStream fis = new FileInputStream(filepath);

        // 读取文件字节数组
//        byte[] bytes = new byte[(int) file.length()];
//        byte[] bytes = new byte[(int) fis.getChannel().size()];
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
//        fis.read(bytes);
//        fis.close();
//        System.out.println(bytes.length);
        return bytes;
    }

//    @PostMapping("/picture_pre")
//    public InputStream openPicture(@RequestBody TbUserFile userFile){
//        String path = "D:\\Repositories\\netdisk\\netdisk_backend\\src\\main\\resources\\upload";
//
//    }

    // 垃圾箱的文件
    @PostMapping ("/updatefile")
    public boolean updateFolderState(@RequestBody TbUserFile file){

        service.updateFile(file.getUserfileId(), file.getIsDel());

        return true;
    }

    @PostMapping ("/deletefile")
    public boolean deleteFolder(@RequestBody TbUserFile file){
        // 删除文件夹
        service.deleteFile(file.getUserfileId());
        return true;
    }

    @PostMapping ("/searchfile")
    public List<TbUserFile> findByFileName(@RequestBody TbUserFile file){
        // 根据文件名称查询文件夹
        return service.findByFileName(file.getBelongUser(),
                file.getFileName(), file.getIsDel());

    }

    @PostMapping ("/filecount")
    public NumsByType getFileCount(@RequestBody TbUserFile file){
        // 构造一个实体用来记录和返回文件数据统计的结果
        NumsByType nums = new NumsByType();

        nums.setPicture(service.getFileCount(file.getBelongUser(), "1", 0).getNums());
        nums.setAudio(service.getFileCount(file.getBelongUser(), "2", 0).getNums());
        nums.setVidio(service.getFileCount(file.getBelongUser(), "3", 0).getNums());
        nums.setWords(service.getFileCount(file.getBelongUser(), "4", 0).getNums());
        nums.setOther(service.getFileCount(file.getBelongUser(), "5", 0).getNums());

        return nums;
    }
}
