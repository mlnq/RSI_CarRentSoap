package models;

import java.io.Serializable;
import java.util.Date;

public class CarRentalImpl implements Serializable{
    private Date dateFrom;
    private Date dateTo;
    private CarImpl car;

    public CarRentalImpl(CarImpl car, Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.car = car;
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
