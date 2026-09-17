package com.aoba.aiqiyi.Entiy;

public class Banner {
    private Integer id;
    private String img_path;

    public Banner() {
    }

    public Banner(Integer id, String img_path) {
        this.id = id;
        this.img_path = img_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", img_path='" + img_path + '\'' +
                '}';
    }
}
