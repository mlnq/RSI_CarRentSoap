package models;

import java.awt.*;

public interface Car {

    public int getId();

    public void setId(int id);

    public int getProductionYear();

    public void setProductionYear(int productionYear);
    public int getMileage();

    public void setMileage(int mileage);

    public String getBrand();

    public void setBrand(String brand);

    public String getModel();

    public void setModel(String model);

    public int getPrice();

    public void setPrice(int price);

    public String getImageName();

    public void setImageName(String imageName);

    public Image getImage();

    public void setImage(Image image);


}
