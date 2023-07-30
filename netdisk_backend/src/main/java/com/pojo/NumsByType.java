package com.pojo;

public class NumsByType {
    private Integer picture;
    private Integer audio;
    private Integer vidio;
    private Integer words;
    private Integer other;

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    public Integer getAudio() {
        return audio;
    }

    public void setAudio(Integer audio) {
        this.audio = audio;
    }

    public Integer getVidio() {
        return vidio;
    }

    public void setVidio(Integer vidio) {
        this.vidio = vidio;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "NumsByType{" +
                "picture=" + picture +
                ", audio=" + audio +
                ", vidio=" + vidio +
                ", words=" + words +
                ", other=" + other +
                '}';
    }
}
