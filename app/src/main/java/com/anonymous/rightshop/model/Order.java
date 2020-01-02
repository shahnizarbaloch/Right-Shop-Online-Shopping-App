package com.anonymous.rightshop.model;

public class Order {
    private String name,price;


    public Order(String name,String price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
