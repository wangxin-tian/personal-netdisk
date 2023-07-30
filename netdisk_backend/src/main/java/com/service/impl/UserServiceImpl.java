package com.service.impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User findUserByUserNameAndPassword(String userName,String userPassword) {
        return mapper.findUserByUserNameAndPassword(userName,userPassword);
    }

    @Override
    public List<User> findAllUser() {
        return mapper.findAllUser();
    }

    @Override
    public void insertUser(String userName, String userPassword, String userEmail, String userNickname) {
        mapper.insertUser(userName, userPassword, userEmail, userNickname);
    }

    @Override
    public void updateUser(Integer userId, String userLevel, Integer driveSize) {
        mapper.updateUser(userId, userLevel, driveSize);
    }

    @Override
    public void updateSelf(Integer userId, String userNickname, String userImgurl) {
        mapper.updateSelf(userId, userNickname, userImgurl);
    }

    @Override
    public void deleteUser(Integer userId) {
        mapper.deleteUser(userId);
    }
}
