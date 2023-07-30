package com.service;

import com.pojo.FileCount;
import com.pojo.TbUserFile;
import com.pojo.User;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_file】的数据库操作Service
* @createDate 2023-03-09 14:54:59
*/
public interface TbUserFileService {

    public List<TbUserFile> findFiles(Integer belongUser, Integer fileLocation, Integer isDel);

    public List<TbUserFile> findAllFiles(Integer belongUser, Integer isDel);


    public void insertUserFile(Integer sysfileId, String fileName, Integer belongUser,
                               String fileType, Integer fileSize, Integer fileLocation,
                               Date uploadTime, Integer isDel);

    public List<TbUserFile> findAllPictures(Integer belongUser, Integer fileType);

    public void updateFile(Integer userfileId, Integer isDel);

    public void deleteFile(Integer userfileId);

    public List<TbUserFile> findByFileName(Integer belongUser, String fileName, Integer isDel);

    public FileCount getFileCount(Integer belongUser, String fileType, Integer isDel);
}
