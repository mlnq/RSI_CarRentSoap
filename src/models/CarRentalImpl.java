package models;

import java.io.Serializable;
import java.util.Date;

public class CarRentalImpl implements Serializable{
    private Date dateFrom;
    private Date dateTo;
    private CarImpl car;
    private String fName;
    private String lName;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public CarRentalImpl(CarImpl car, Date dateFrom, Date dateTo, String fName, String lName, int id ) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.car = car;
        this.fName = fName;
        this.lName = lName;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public CarImpl getCar() {
        return car;
    }

    public void setCar(CarImpl car) {
        this.car = car;
    }
}
