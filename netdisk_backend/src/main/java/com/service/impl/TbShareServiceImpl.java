package com.service.impl;

import com.pojo.TbShare;
import com.service.TbShareService;
import com.dao.TbShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_share】的数据库操作Service实现
* @createDate 2023-03-14 22:16:41
*/
@Service
public class TbShareServiceImpl implements TbShareService{

    @Autowired
    TbShareMapper mapper;

    @Override
    public List<TbShare> findALLShareFile() {
        return mapper.findALLShareFile();
    }

    @Override
    public void insertShareFile(Integer shareUser, Integer shareUserfileId, String shareComment, String fileLocation, Date shareDate) {
        mapper.insertShareFile( shareUser, shareUserfileId, shareComment, fileLocation, shareDate);
    }
}
