package com.service;


import com.pojo.TbShare;

import java.util.Date;
import java.util.List;

/**
* @author 31065
* @description 针对表【tb_share】的数据库操作Service
* @createDate 2023-03-14 22:16:41
*/
public interface TbShareService {
    public List<TbShare> findALLShareFile();

    public void insertShareFile(Integer shareUser, Integer shareUserfileId,
                                String shareComment, String fileLocation, Date shareDate);

}
