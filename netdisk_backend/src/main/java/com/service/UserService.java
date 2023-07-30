package com.service;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //查询所有
    public User findUserByUserNameAndPassword(String userName,String userPassword);

    public List<User> findAllUser();

    public void insertUser(String userName, String userPassword,
                            String userEmail, String userNickname);

    public void updateUser(Integer userId, String userLevel, Integer driveSize);

    public void updateSelf(Integer userId, String userNickname, String userImgurl);

    public void deleteUser(Integer userId);
}
