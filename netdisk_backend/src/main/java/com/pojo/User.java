package com.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User  implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userLevel;
    private Integer driveSize;
    private String userNickname;
    private String userImgurl;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getDriveSize() {
        return driveSize;
    }

    public void setDriveSize(Integer driveSize) {
        this.driveSize = driveSize;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserImgurl() {
        return userImgurl;
    }

    public void setUserImgurl(String userImgurl) {
        this.userImgurl = userImgurl;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", driveSize=" + driveSize +
                ", userNickname='" + userNickname + '\'' +
                ", userImgurl='" + userImgurl + '\'' +
                '}';
    }
}
