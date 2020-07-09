package com.example.shoppingmall.entity;

public class Order {
    private String orderId;
    private String receiver;
    private String address;
    private String phone;
    private String orderMoney;
    private String orderState;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}
