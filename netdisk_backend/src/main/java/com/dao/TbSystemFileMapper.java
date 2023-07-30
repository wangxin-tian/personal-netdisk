package com.dao;


import com.pojo.TbUserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
* @author 31065
* @description 针对表【tb_system_file】的数据库操作Mapper
* @createDate 2023-03-14 21:34:07
* @Entity com.pojo.TbSystemFile
*/
@Mapper
public interface TbSystemFileMapper{

    void insertSystemFile(@Param("fileName")String fileName, @Param("fileUrl") String fileUrl,
                                @Param("belongUser") Integer belongUser, @Param("fileType") String fileType,
                                @Param("fileSize") Integer fileSize, @Param("uploadTime") Date uploadTime);

}
