package com.service;

import com.pojo.TbFolder;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_folder】的数据库操作Service
* @createDate 2023-03-14 22:16:41
*/
public interface TbFolderService {

    public List<TbFolder> findAllFolders(Integer folderFather, Integer belongUser, Integer isdel);

    public List<TbFolder> findFolders(Integer belongUser, Integer isdel);

    public void updateFolder(Integer folderId, Integer isdel);

    public void deleteFolder(Integer folderId);

    public List<TbFolder> findByFolderName(Integer belongUser, String folderName, Integer isdel);

    public void insertUserFolder(String folderName, Integer folderFather, Integer belongUser
            , Date folderCreatetime, Integer isdel);
}
