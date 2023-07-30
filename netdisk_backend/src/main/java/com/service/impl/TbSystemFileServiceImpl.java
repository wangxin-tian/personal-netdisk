package com.service.impl;

import com.pojo.TbSystemFile;
import com.pojo.TbUserFile;
import com.service.TbSystemFileService;
import com.dao.TbSystemFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 31065
* @description 针对表【tb_system_file】的数据库操作Service实现
* @createDate 2023-03-14 21:34:07
*/
@Service
public class TbSystemFileServiceImpl implements TbSystemFileService{

    @Autowired
    TbSystemFileMapper mapper;

    @Override
    public void insertSystemFile(String fileName, String fileUrl, Integer belongUser, String fileType, Integer fileSize, Date uploadTime) {
        mapper.insertSystemFile(fileName,fileUrl,belongUser,fileType,fileSize,uploadTime);
    }
}
