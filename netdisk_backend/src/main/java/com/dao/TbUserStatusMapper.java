package com.dao;

import com.pojo.TbUserStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_status】的数据库操作Mapper
* @createDate 2023-03-14 22:16:41
* @Entity com.pojo.TbUserStatus
*/
@Mapper
public interface TbUserStatusMapper {


    List<TbUserStatus> selectALLuserStatus();

    void insertUserState(@Param("userName") String userName, @Param("userStatus") Integer userStatus);

    void updateUserStatus(@Param("userName") String userName,@Param("userStatus") Integer userStatus);
}
