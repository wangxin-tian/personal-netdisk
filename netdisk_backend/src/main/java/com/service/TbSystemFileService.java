package com.service;

import com.pojo.TbSystemFile;
import com.pojo.TbUserFile;

import java.util.Date;

/**
* @author 31065
* @description 针对表【tb_system_file】的数据库操作Service
* @createDate 2023-03-14 21:34:07
*/
public interface TbSystemFileService{

    public void insertSystemFile(String fileName, String fileUrl, Integer belongUser
            , String fileType, Integer fileSize, Date uploadTime);
}
