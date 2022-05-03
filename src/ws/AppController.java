package ws;


import models.Car;
import models.CarImpl;
import models.CarRentalImpl;
import models.CarReservationImpl;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class AppController {

    private List<CarImpl> cars = new ArrayList<CarImpl>();
    private List<CarRentalImpl> rentedCars = new ArrayList<CarRentalImpl>();
    private List<CarReservationImpl> reservedCars = new ArrayList<CarReservationImpl>();

    //inicjalizacja Seedera
    public AppController() throws Exception {
        CreateCars();
        CreateRenatalCars();
        CreateReservedCars();
    }

    // Metody Car------------------------------------------------------------------------------------------------
   private void CreateCars() throws Exception {
        AddCar("Mitshubishi", "Colt", 2004, 2000000,100, true, false);
        AddCar("Citroen", "c4", 2012, 4000000,200, false, false);
        AddCar("Seat", "Leon", 2014, 115000,1000, false, false);
        AddCar("Opel", "Omega", 2002, 220000,300, false, false);
        AddCar("Dodge", "Durango", 2017, 131000,780, true, false);
        AddCar("Jaguar", "F-Type", 2021, 100000,2000, true, false);
        AddCar("BMW", "X6", 2019, 62226,3000, false, false);
    }

   public void AddCar(String carBrand, String carModel, int productionYear, int mileage, int price, boolean isReserved, boolean isRented) throws Exception {
        CarImpl newCar = new CarImpl(Integer.parseInt(String.valueOf(cars.size())), carBrand, carModel, productionYear, mileage,price, isReserved, isRented);
        cars.add(newCar);
    }

    public int DeleteCar(int id)
    {
        for (Car car : cars) {
            if (car.getId() == id) {
                cars.remove(car);
                return 200;
            }
        }
        return 204;
    }

    public Object GetCar(int id)
    {
        for (Car car : cars) {
            if (car.getId() == id) {
               return car;
            }
        }
        return null;
    }

    public void UpdateCar(int id, String carBrand, String carModel, int productionYear, int mileage, int price)
    {
        Car car = (Car) GetCar(id);
        car.setBrand(carBrand);
        car.setMileage(mileage);
        car.setPrice(price);
        car.setProductionYear(productionYear);
        car.setModel(carModel);
    }
    public List<CarImpl> GetCars() {
        return cars;
    }

    //Metody Rental------------------------------------------------------------------------------------------------
    public void CreateRenatalCars()
    {
//        AddRentedCars(0,LocalDate.of(2022,7,22),LocalDate.of(2022,7,30));
        AddRentedCars(0,new Date (2022,7,22),new Date(2022,7,30));
    }

    public void AddRentedCars(int carId, Date dateFrom, Date dateTo){
        CarImpl car = (CarImpl) GetCar(carId);
        if(car.isRented() || car.isReserved()){
            return;
        }
        dateFrom.setYear(dateFrom.getYear() - 1900);
        dateFrom.setMonth(dateFrom.getMonth()-1);
        dateTo.setYear(dateTo.getYear() - 1900);
        dateTo.setMonth(dateTo.getMonth()-1);
        car.setRented(true);
        rentedCars.add(new CarRentalImpl(car, dateFrom, dateTo));
    }

    public List<CarRentalImpl> GetRentedCars(){
        return rentedCars;
    }

    public int DeleteRentedCar(int id)
    {
        for (CarRentalImpl carRental : rentedCars){
            if(carRental.getCar().getId() == id) {
                rentedCars.remove(carRental);
                carRental.getCar().setRented(false);
                return 200;
            }
        }
        return 204;
    }

    //Metody Reservation
    public void CreateReservedCars()
    {
        AddReservedCars(3,new Date (2022,10,13),new Date(2022,11,30));
    }

    public void AddReservedCars(int carId, Date dateFrom, Date dateTo){
        CarImpl car = (CarImpl) GetCar(carId);
        if(car.isReserved() || car.isRented()){
            return;
        }
        dateFrom.setYear(dateFrom.getYear() - 1900);
        dateFrom.setMonth(dateFrom.getMonth()-1);
        dateTo.setYear(dateTo.getYear() - 1900);
        dateTo.setMonth(dateTo.getMonth()-1);
        car.setRented(true);
        rentedCars.add(new CarReservationImpl(car, dateFrom, dateTo));
    }

    public List<CarReservationImpl> GetReservedCars(){
        return reservedCars;
    }

    public int DeleteReservedCar(int id)
    {
        for (CarReservationImpl carReserved : reservedCars){
            if(carReserved.getCar().getId() == id) {
                reservedCars.remove(carReserved);
                carReserved.getCar().setReserved(false);
                return 200;
            }
        }
        return 204;
    }



}
