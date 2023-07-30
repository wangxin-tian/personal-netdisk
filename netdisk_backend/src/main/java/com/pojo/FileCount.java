package com.pojo;

public class FileCount {
    private String type;
    private Integer nums;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "FileCount{" +
                "type='" + type + '\'' +
                ", nums=" + nums +
                '}';
    }
}
