package com.service.impl;

import com.pojo.TbUserStatus;
import com.service.TbUserStatusService;
import com.dao.TbUserStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 31065
* @description 针对表【tb_user_status】的数据库操作Service实现
* @createDate 2023-03-14 22:16:41
*/
@Service
public class TbUserStatusServiceImpl implements TbUserStatusService{

    @Autowired
    TbUserStatusMapper mapper;

    @Override
    public List<TbUserStatus> selectALLuserStatus() {
        return mapper.selectALLuserStatus();
    }

    @Override
    public void insertUserState(String userName, Integer userStatus) {
        mapper.insertUserState(userName, userStatus);
    }

    @Override
    public void updateUserStatus(String userName, Integer userStatus) {
        mapper.updateUserStatus(userName, userStatus);
    }
}
