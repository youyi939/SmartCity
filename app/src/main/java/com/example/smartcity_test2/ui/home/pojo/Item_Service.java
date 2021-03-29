package com.example.smartcity_test2.ui.home.pojo;

import java.io.Serializable;

public class Item_Service implements Serializable {
    private String img;
    private String serviceName;
    private Integer id;
    private String serviceDesc;

    public Item_Service(String img, String serviceName, Integer id, String serviceDesc) {
        this.img = img;
        this.serviceName = serviceName;
        this.id = id;
        this.serviceDesc = serviceDesc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    @Override
    public String toString() {
        return "Item{" +
                "img='" + img + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", id=" + id +
                ", serviceDesc='" + serviceDesc + '\'' +
                '}';
    }
}
