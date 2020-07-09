package com.example.shoppingmall.entity;

public class Cart {
    private String goodId;
    private String goodImage;
    private String goodName;
    private String goodValue;
    private String goodNumber;
    private boolean isSelected;

    public Cart(String goodId, String goodImage, String goodName, String goodValue, String goodNumber, boolean isSelected) {
        this.goodId = goodId;
        this.goodImage = goodImage;
        this.goodName = goodName;
        this.goodValue = goodValue;
        this.goodNumber = goodNumber;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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

    public String getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        this.goodNumber = goodNumber;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "goodId='" + goodId + '\'' +
                ", goodImage='" + goodImage + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodValue='" + goodValue + '\'' +
                ", goodNumber='" + goodNumber + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
