package com.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_folder
 */
public class TbFolder implements Serializable {

    private Integer folderId;
    private String folderName;
    private Integer folderFather;
    private Integer belongUser;
    private Date folderCreatetime;
    private Integer isdel;

    private static final long serialVersionUID = 1L;

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Integer getFolderFather() {
        return folderFather;
    }

    public void setFolderFather(Integer folderFather) {
        this.folderFather = folderFather;
    }

    public Integer getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(Integer belongUser) {
        this.belongUser = belongUser;
    }

    public Date getFolderCreatetime() {
        return folderCreatetime;
    }

    public void setFolderCreatetime(Date folderCreatetime) {
        this.folderCreatetime = folderCreatetime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "TbFolder{" +
                "folderId=" + folderId +
                ", folderName='" + folderName + '\'' +
                ", folderFather=" + folderFather +
                ", belongUser=" + belongUser +
                ", folderCreatetime=" + folderCreatetime +
                ", isdel=" + isdel +
                '}';
    }
}