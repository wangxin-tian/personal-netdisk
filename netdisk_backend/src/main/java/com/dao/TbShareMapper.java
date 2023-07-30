package com.dao;

import com.pojo.TbShare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_share】的数据库操作Mapper
* @createDate 2023-03-14 22:16:41
* @Entity com.pojo.TbShare
*/
@Mapper
public interface TbShareMapper {


    void insertShareFile(@Param("shareUser") Integer shareUser,
                         @Param("shareUserfileId") Integer shareUserfileId,
                         @Param("shareComment") String shareComment,
                         @Param("fileLocation") String fileLocation,
                         @Param("shareDate") Date shareDate);

    List<TbShare> findALLShareFile();
}
