package models;

import java.io.Serializable;
import java.util.Date;

public class CarReservationImpl extends CarRentalImpl implements Serializable {

    public CarReservationImpl(CarImpl car, Date dateFrom, Date dateTo, String firstName, String lastName, int id) {
        super(car, dateFrom, dateTo, firstName, lastName, id);
    }
}
