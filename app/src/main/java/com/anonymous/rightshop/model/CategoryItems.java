package com.anonymous.rightshop.model;

public class CategoryItems {
    private String image;
    private String id,name,oldPrice,newPrice,serialNumber,details;
    private long ID;


    public CategoryItems(String id,String image, String name, String oldPrice, String newPrice){
        this.id=id;
        this.image=image;
        this.name=name;
        this.oldPrice=oldPrice;
        this.newPrice=newPrice;
    }

    public CategoryItems(String id,String image, String name, String details, String oldPrice, String newPrice,String serialNumber){
        this.id=id;
        this.image=image;
        this.name=name;
        this.details=details;
        this.oldPrice=oldPrice;
        this.newPrice=newPrice;
        this.serialNumber=serialNumber;
    }

    public CategoryItems(long ID, String name, String oldPrice,String serialNumber){
        this.ID=ID;
        this.name=name;
        this.oldPrice=oldPrice;
        this.serialNumber=serialNumber;
    }

    public CategoryItems(long ID, String name, String oldPrice, String serialNumber,String imageUrl){
        this.ID=ID;
        this.name=name;
        this.oldPrice=oldPrice;
        this.serialNumber=serialNumber;
        this.image=imageUrl;
    }

    public CategoryItems(Long ID,String name, String oldPrice, String serialNumber,String image){
        this.ID=ID;
        this.name=name;
        this.oldPrice=oldPrice;
        this.serialNumber=serialNumber;
        this.image=image;
    }





    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }


    public String getOldPrice() {
        return oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDetails() {
        return details;
    }
}
