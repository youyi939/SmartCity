package com.example.smartcity_test2.ui.home.pojo;

import java.io.Serializable;
import java.util.List;

public class ItemNew implements Serializable {
    private String label;           //标签
    private Integer code;           //识别码
    private List<Item> itemList;    //item新闻主体

    public ItemNew(String label, Integer code, List<Item> itemList) {
        this.label = label;
        this.code = code;
        this.itemList = itemList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "ItemNew{" +
                "label='" + label + '\'' +
                ", code=" + code +
                ", itemList=" + itemList +
                '}';
    }
}
