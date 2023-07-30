package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User findUser(@RequestBody User user){
        User userLoca = userService.
                findUserByUserNameAndPassword(user.getUserName(),user.getUserPassword());
        // userLoca.setUserPassword(null);
        return userLoca;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        System.out.println(user);
        //检查用户名密码是否重复
        User userLoca = userService.
                findUserByUserNameAndPassword(user.getUserName(),user.getUserPassword());
        if( userLoca == null ) {
            userService.insertUser(user.getUserName(), user.getUserPassword(),
                    user.getUserEmail(), user.getUserNickname());
        } else {
            return null;
        }
        return userService.findUserByUserNameAndPassword(user.getUserName(),user.getUserPassword());
    }

    @GetMapping("/allusers")
    public List<User> findAllUser(){
        // 查询全部用户
        return userService.findAllUser();
    }

    @PostMapping("/upuser")
    public boolean updateUser(@RequestBody User user) {
        // 根据id，修改用户等级和空间大小
        userService.updateUser(user.getUserId(),
                user.getUserLevel(),
                user.getDriveSize());
        return true;
    }

    @PostMapping("/upself")
    public boolean updateSelf(@RequestBody User user) {
        // 根据id，修改用户网名和头像
        userService.updateSelf(user.getUserId(),
                user.getUserNickname(),
                user.getUserImgurl());
        return true;
    }

    @PostMapping("/deleteuser")
    public boolean deleteUser(@RequestBody User user) {
        // 根据id删除用户
        userService.deleteUser( user.getUserId());
        return true;
    }


}
