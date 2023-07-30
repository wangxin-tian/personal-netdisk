package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //用户名密码查询用户信息
    public User findUserByUserNameAndPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

    //查询所有用户
    public List<User> findAllUser();

    public void insertUser(@Param("userName") String userName, @Param("userPassword") String userPassword,
                           @Param("userEmail") String userEmail, @Param("userNickname") String userNickname);

    void updateUser(@Param("userId") Integer userId,
                    @Param("userLevel") String userLevel,
                    @Param("driveSize") Integer driveSize);

    void deleteUser(@Param("userId") Integer userId);

    void updateSelf(@Param("userId") Integer userId,
                    @Param("userNickname") String userNickname,
                    @Param("userImgurl") String userImgurl);
}
