package com.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_share
 */
public class TbShare implements Serializable {

    private Integer shareId;
    private Integer shareUser;
    private Integer shareUserfileId;
    private String shareComment;
    private String fileLocation;
    private Date shareDate;

    private static final long serialVersionUID = 1L;

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getShareUser() {
        return shareUser;
    }

    public void setShareUser(Integer shareUser) {
        this.shareUser = shareUser;
    }

    public Integer getShareUserfileId() {
        return shareUserfileId;
    }

    public void setShareUserfileId(Integer shareUserfileId) {
        this.shareUserfileId = shareUserfileId;
    }

    public String getShareComment() {
        return shareComment;
    }

    public void setShareComment(String shareComment) {
        this.shareComment = shareComment;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }

    @Override
    public String toString() {
        return "TbShare{" +
                "shareId=" + shareId +
                ", shareUser=" + shareUser +
                ", shareUserfileId=" + shareUserfileId +
                ", shareComment='" + shareComment + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                ", shareDate=" + shareDate +
                '}';
    }
}