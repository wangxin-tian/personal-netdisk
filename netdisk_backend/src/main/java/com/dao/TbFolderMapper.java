package com.dao;

import com.pojo.TbFolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_folder】的数据库操作Mapper
* @createDate 2023-03-14 22:16:41
* @Entity com.pojo.TbFolder
*/
@Mapper
public interface TbFolderMapper {
    List<TbFolder> findAllFolders(@Param("folderFather") Integer folderFather,
                                  @Param("belongUser") Integer belongUser,
                                  @Param("isdel") Integer isdel);

    List<TbFolder> findFolders(@Param("belongUser") Integer belongUser,
                               @Param("isdel") Integer isdel);

    void updateFolder(@Param("folderId")Integer folderId,
                      @Param("isdel") Integer isdel);

    void deleteFolder(@Param("folderId") Integer folderId);

    List<TbFolder> findByFolderName(@Param("belongUser") Integer belongUser,
                                    @Param("folderName") String folderName,
                                    @Param("isdel") Integer isdel);

    void insertUserFolder(@Param("folderName") String folderName,
                          @Param("folderFather") Integer folderFather,
                          @Param("belongUser") Integer belongUser,
                          @Param("folderCreatetime") Date folderCreatetime,
                          @Param("isdel") Integer isdel);
}
