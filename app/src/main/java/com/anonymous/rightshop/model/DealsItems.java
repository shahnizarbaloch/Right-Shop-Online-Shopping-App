package com.anonymous.rightshop.model;

public class DealsItems {
    private String id,Url,Name,Price,Serial;

    public DealsItems (String id, String Url,String Name,String Price,String Serial){
        this.id = id;
        this.Url = Url;
        this.Name = Name;
        this.Price= Price;
        this.Serial = Serial;
    }

    public DealsItems (String Url,String Name,String Price){
        this.Url = Url;
        this.Name=Name;
        this.Price=Price;
    }



    public String getUrl() {
        return Url;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getId() {
        return id;
    }

    public String getSerial() {
        return Serial;
    }
}
