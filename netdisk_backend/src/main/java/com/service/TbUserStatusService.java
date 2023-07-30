package com.service;

import com.pojo.TbShare;
import com.pojo.TbUserStatus;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_status】的数据库操作Service
* @createDate 2023-03-14 22:16:41
*/
public interface TbUserStatusService {

    public List<TbUserStatus> selectALLuserStatus();

    public void insertUserState(String userName, Integer userStatus);

    public void updateUserStatus(String userName, Integer userStatus);
}
