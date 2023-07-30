package com.pojo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class TbUserFile  implements Serializable {

    private Integer userfileId;
    private Integer sysfileId;
    private String fileName;
    private Integer belongUser;
    private String fileType;
    private Integer fileSize;
    private Integer fileLocation;
    private Date uploadTime;
    private Integer isDel;

    public Integer getUserfileId() {
        return userfileId;
    }

    public void setUserfileId(Integer userfileId) {
        this.userfileId = userfileId;
    }

    public Integer getSysfileId() {
        return sysfileId;
    }

    public void setSysfileId(Integer sysfileId) {
        this.sysfileId = sysfileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(Integer belongUser) {
        this.belongUser = belongUser;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(Integer fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "TbUserFile{" +
                "userfileId=" + userfileId +
                ", sysfileId=" + sysfileId +
                ", fileName='" + fileName + '\'' +
                ", belongUser=" + belongUser +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", fileLocation=" + fileLocation +
                ", uploadTime=" + uploadTime +
                ", isDel=" + isDel +
                '}';
    }
}