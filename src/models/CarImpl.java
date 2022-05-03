package models;

import java.io.Serializable;

public class CarImpl implements Serializable,Car {

    private int id;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private int price;
    private boolean isRented;
    private boolean isReserved;

    public CarImpl(int id, String brand, String model, int productionYear, int mileage, int price, boolean isReserved, boolean isRented) {
        this.id=id;
        this.brand =brand;
        this.model =model;
        this.productionYear=productionYear;
        this.mileage=mileage;
        this.price=price;
        this.isReserved=isReserved;
        this.isRented=isRented;
    }

//getters & setters


    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


