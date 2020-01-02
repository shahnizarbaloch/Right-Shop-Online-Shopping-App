package com.anonymous.rightshop.model;

public class CartItem {

    private String name,oldPrice,serialNumber,image;
    private Long id;

    public CartItem(String name, String oldPrice, String serialNumber,String image){
        this.name=name;
        this.oldPrice=oldPrice;
        this.serialNumber=serialNumber;
        this.image=image;
    }

    public CartItem(Long id,String name, String oldPrice, String serialNumber,String image){
        this.id=id;
        this.name=name;
        this.oldPrice=oldPrice;
        this.serialNumber=serialNumber;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getImage() {
        return image;
    }

    public Long getId() {
        return id;
    }
}
