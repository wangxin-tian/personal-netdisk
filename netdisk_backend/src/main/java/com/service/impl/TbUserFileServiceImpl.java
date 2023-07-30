package com.service.impl;


import com.dao.TbUserFileMapper;
import com.pojo.FileCount;
import com.pojo.TbUserFile;
import com.service.TbUserFileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_file】的数据库操作Service实现
* @createDate 2023-03-09 14:54:59
*/
@Service
public class TbUserFileServiceImpl implements TbUserFileService{

    @Autowired
    TbUserFileMapper mapper;

    @Override
    public List<TbUserFile> findFiles(Integer belongUser, Integer fileLocation, Integer isDel) {
        return mapper.findFiles(belongUser, fileLocation, isDel);
    }

    @Override
    public List<TbUserFile> findAllFiles(Integer belongUser, Integer isDel) {
        return mapper.findAllFiles(belongUser, isDel);
    }

    @Override
    public void insertUserFile(Integer sysfileId, String fileName, Integer belongUser,
                               String fileType, Integer fileSize, Integer fileLocation,
                               Date uploadTime, Integer isDel) {
        mapper.insertUserFile(sysfileId, fileName, belongUser,
                fileType, fileSize, fileLocation, uploadTime, isDel);
    }

    @Override
    public List<TbUserFile> findAllPictures(Integer belongUser, Integer fileType) {
        return mapper.findAllPictures(belongUser,fileType);
    }

    @Override
    public void updateFile(Integer userfileId, Integer isDel) {
        mapper.updateFile(userfileId, isDel);
    }

    @Override
    public void deleteFile(Integer userfileId) {
        mapper.deleteFile(userfileId);
    }

    @Override
    public List<TbUserFile> findByFileName(Integer belongUser, String fileName, Integer isDel) {
        return mapper.findByFileName(belongUser, fileName, isDel);
    }

    @Override
    public FileCount getFileCount(Integer belongUser, String fileType, Integer isDel) {
        return mapper.getFileCount(belongUser, fileType, isDel);
    }
}
