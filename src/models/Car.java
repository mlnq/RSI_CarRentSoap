package models;

import java.io.Serializable;

public class Car implements Serializable {

    private int id;
    public String brand;
    public String model;
    private int productionYear;
    private int mileage;
    private int price;

    public Car(int id, String brand, String model, int productionYear, int mileage, int price) {
        this.id=id;
        this.brand =brand;
        this.model =model;
        this.productionYear=productionYear;
        this.mileage=mileage;
        this.price=price;
    }

//getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


