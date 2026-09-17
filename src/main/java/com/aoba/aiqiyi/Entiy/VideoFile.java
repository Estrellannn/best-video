package com.aoba.aiqiyi.Entiy;

public class VideoFile {
    private Integer id;
    private Integer v_id;
    private Integer num;
    private String videopath;
    private String name;

    public VideoFile() {
    }

    public VideoFile(Integer id, Integer v_id, Integer num, String videopath, String name) {
        this.id = id;
        this.v_id = v_id;
        this.num = num;
        this.videopath = videopath;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getV_id() {
        return v_id;
    }

    public void setV_id(Integer v_id) {
        this.v_id = v_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
