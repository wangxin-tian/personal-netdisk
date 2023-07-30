package com.controller;

import com.pojo.TbShare;
import com.pojo.TbUserStatus;
import com.service.TbUserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080",allowCredentials = "true")//解决session跨域
@RequestMapping("/status")
public class UserStatus {

    @Autowired
    TbUserStatusService service;

    @PostMapping("/insert")
    public boolean insertUserState(@RequestBody TbUserStatus status){

        service.insertUserState( status.getUserName(), status.getUserStatus() );

        return true;
    }

    @GetMapping("/allstatus")
    public List<TbUserStatus> selectALLuserStatus() {

        return service.selectALLuserStatus();

    }

    @PostMapping("/update")
    public boolean updateUserStatus(@RequestBody TbUserStatus status){

        service.updateUserStatus( status.getUserName(), status.getUserStatus() );

        return true;
    }
}
