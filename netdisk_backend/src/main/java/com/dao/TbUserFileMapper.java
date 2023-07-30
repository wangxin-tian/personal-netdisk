package com.dao;

import com.pojo.FileCount;
import com.pojo.TbUserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_file】的数据库操作Mapper
* @createDate 2023-03-09 14:54:59
* @Entity com.pojo.TbUserFile
*/
@Mapper
public interface TbUserFileMapper{
    public List<TbUserFile> findFiles(@Param("belongUser") Integer belongUser,
                                             @Param("fileLocation")Integer fileLocation,
                                             @Param("isDel")Integer isDel);


    public void insertUserFile(@Param("sysfileId") Integer sysfileId,
                               @Param("fileName") String fileName,
                               @Param("belongUser") Integer belongUser,
                               @Param("fileType") String fileType,
                               @Param("fileSize") Integer fileSize,
                               @Param("fileLocation") Integer fileLocation,
                               @Param("uploadTime") Date uploadTime,
                               @Param("isDel") Integer isDel);

    List<TbUserFile> findAllPictures(@Param("belongUser") Integer belongUser,
                                     @Param("fileType") Integer fileType);

    List<TbUserFile> findAllFiles(@Param("belongUser") Integer belongUser,
                                  @Param("isDel") Integer isDel);

    void updateFile(@Param("userfileId") Integer userfileId,
                    @Param("isDel") Integer isDel);

    void deleteFile(@Param("userfileId") Integer userfileId);

    List<TbUserFile> findByFileName(@Param("belongUser") Integer belongUser,
                                    @Param("fileName") String fileName,
                                    @Param("isDel") Integer isDel);

    FileCount getFileCount(@Param("belongUser") Integer belongUser,
                                 @Param("fileType") String fileType,
                                 @Param("isDel") Integer isDel);
}
