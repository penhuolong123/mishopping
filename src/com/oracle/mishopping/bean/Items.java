package com.oracle.mishopping.bean;


import java.io.Serializable;

public class Items{
    private Products product;//��Ʒ����
    private int num;//��Ʒ����

    public Items() {
    }

    public Items(int num,Products product) {
        this.num = num;
        this.product = product;

    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
