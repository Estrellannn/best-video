package com.aoba.aiqiyi.Entiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Redis 底层只能存二进制字节，Java 对象必须序列化才能存入 Redis；
//实现Serializable接口就是告诉 JVM：这个类可以转字节存入缓存。
public class Video implements Serializable {
    private Integer id;
    private String title;
    private Integer episodes;
    private String cover_img;
    private String category;
    private String info;
    private String playUrl;
    // 一对多：当前影视绑定的所有分类

    // 修改为初始化空集合
    private List<Category> categoryList = new ArrayList<>();
    // 临时字段，XML查询使用，无需数据库字段
    private Integer cat_id;
    private String cat_name;

    public Video() {
    }

    public Video(Integer id, String title, Integer episodes, String cover_img, String category) {
        this.id = id;
        this.title = title;
        this.episodes = episodes;
        this.cover_img = cover_img;
        this.category = category;
    }

    public Video(String info, Integer id, String title, Integer episodes, String cover_img) {
        this.info = info;
        this.id = id;
        this.title = title;
        this.episodes = episodes;
        this.cover_img = cover_img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }
}
