package com.example.smartcity_test2.ui.home.pojo;

/**
 * 轮播图对象
 */
public class Img {
    private int id;
    private int sort;
    private String imgUrl;


    public Img(int id, int sort, String imgUrl) {
        this.id = id;
        this.sort = sort;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", sort=" + sort +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
