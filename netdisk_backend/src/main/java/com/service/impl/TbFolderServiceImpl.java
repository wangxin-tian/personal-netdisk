package com.service.impl;

import com.pojo.TbFolder;
import com.service.TbFolderService;
import com.dao.TbFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_folder】的数据库操作Service实现
* @createDate 2023-03-14 22:16:41
*/
@Service
public class TbFolderServiceImpl implements TbFolderService{

    @Autowired
    TbFolderMapper mapper;

    @Override
    public List<TbFolder> findAllFolders(Integer folderFather, Integer belongUser, Integer isdel) {
        return mapper.findAllFolders(folderFather, belongUser, isdel);
    }

    @Override
    public List<TbFolder> findFolders(Integer belongUser, Integer isdel) {
        return mapper.findFolders(belongUser, isdel);
    }

    @Override
    public void updateFolder(Integer folderId, Integer isdel) {
        mapper.updateFolder(folderId, isdel);
    }

    @Override
    public void deleteFolder(Integer folderId) {
        mapper.deleteFolder(folderId);
    }

    @Override
    public List<TbFolder> findByFolderName(Integer belongUser, String folderName, Integer isdel) {
        return mapper.findByFolderName(belongUser, folderName, isdel);
    }

    @Override
    public void insertUserFolder(String folderName, Integer folderFather, Integer belongUser, Date folderCreatetime, Integer isdel) {
        mapper.insertUserFolder(folderName, folderFather, belongUser, folderCreatetime, isdel);
    }
}
