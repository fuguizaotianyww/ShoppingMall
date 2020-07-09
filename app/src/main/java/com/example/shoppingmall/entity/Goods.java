package com.example.shoppingmall.entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private String goodId;
    private String goodImage;
    private String goodName;
    private String goodValue;
    private String goodIntroduction;


    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodValue() {
        return goodValue;
    }

    public void setGoodValue(String goodValue) {
        this.goodValue = goodValue;
    }

    public String getGoodIntroduction() {
        return goodIntroduction;
    }

    public void setGoodIntroduction(String goodIntroduction) {
        this.goodIntroduction = goodIntroduction;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId='" + goodId + '\'' +
                ", goodImage='" + goodImage + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodValue='" + goodValue + '\'' +
                ", goodIntroduction='" + goodIntroduction + '\'' +
                '}';
    }
}
