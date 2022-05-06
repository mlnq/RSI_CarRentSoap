package models;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.Serializable;
import java.util.Base64;

@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class CarImpl implements Serializable,Car {

    private int id;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private int price;
    private boolean isRented;
    private boolean isReserved;

    private String imageName;

    private Image image;

    public CarImpl(int id, String brand, String model, int productionYear, int mileage, int price, boolean isReserved, boolean isRented, String imageName, Image carImage) {
        this.id=id;
        this.brand =brand;
        this.model =model;
        this.productionYear=productionYear;
        this.mileage=mileage;
        this.price=price;
        this.isReserved=isReserved;
        this.isRented=isRented;
        this.imageName = imageName;
        this.image = carImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return this.id + " " + this.brand + " " + this.model+ " " + this.productionYear + " " + this.mileage + " " + this.price + " " + this.isReserved + " " + this.isRented + " " + this.image;
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


