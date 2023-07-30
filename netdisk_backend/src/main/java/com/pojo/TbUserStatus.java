package com.pojo;

import java.io.Serializable;

/**
 * 
 * @TableName tb_user_status
 */
public class TbUserStatus{

    private String userName;
    private Integer userStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "TbUserStatus{" +
                "userName='" + userName + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}