package ws;




import models.Car;
import models.CarImpl;
import models.CarRentalImpl;
import models.CarReservationImpl;

import javax.imageio.ImageIO;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@HandlerChain(file="handler-chain.xml")
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

    public Image downloadImage(String name) {
        try {
            //ścieżka Natala
//            File image = new File("C:\\Users\\dell\\IdeaProjects\\RSI_CarRentSoap\\images\\" + name);
            //ścieżka Michał
           File image = new File("C:\\Users\\mmsta\\OneDrive\\Pulpit\\RSI_car\\CarSoap\\images\\" + name);

            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metody Car------------------------------------------------------------------------------------------------
   private void CreateCars() throws Exception {
        AddCar("Mitshubishi", "Colt", 2004, 2000000,100, true, false, "mitsubishi_colt.jpg");
        AddCar("Citroen", "c4", 2012, 4000000,200, false, false, "citroen_c4.jpg");
        AddCar("Seat", "Leon", 2014, 115000,1000, false, false, "seat_leon.jpg");
        AddCar("Opel", "Omega", 2002, 220000,300, false, false, "opel_omega.jpg");
        AddCar("Dodge", "Durango", 2017, 131000,780, true, false, "dodge_durango.jpg");
        AddCar("Jaguar", "F-Type", 2021, 100000,2000, true, true, "jaguar.jpg");
        AddCar("BMW", "X6", 2019, 62226,3000, false, false,"bmw_x6.jpg");
    }

   public void AddCar(String carBrand, String carModel, int productionYear, int mileage, int price, boolean isReserved, boolean isRented, String imageName) throws Exception {
        CarImpl newCar = new CarImpl(Integer.parseInt(String.valueOf(cars.size())), carBrand, carModel, productionYear, mileage,price, isReserved, isRented, imageName, downloadImage(imageName));
        cars.add(newCar);
       System.out.println(newCar);
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
        AddRentedCars(0,new Date (2022-1900,7-1,22),new Date(2022-1900,7-1,30), "Robert", "Nowak");
    }

    public boolean AddRentedCars(int carId, Date dateFrom, Date dateTo, String fName, String lName){
        CarImpl car = (CarImpl) GetCar(carId);
        if(!(car.isRented() || car.isReserved())){
            return false;
        }
        car.setRented(true);
        rentedCars.add(new CarRentalImpl(car, dateFrom, dateTo, fName, lName, Integer.parseInt(String.valueOf(rentedCars.size()))));
        return true;
    }

    public List<CarRentalImpl> GetRentedCars(){
        return rentedCars;
    }

    public boolean DeleteRentedCar(int id)
    {
        for (CarRentalImpl carRental : rentedCars){
            if(carRental.getCar().getId() == id) {
                rentedCars.remove(carRental);
                carRental.getCar().setRented(false);
                DeleteReservedCar(id);
                return true;
            }
        }
        return false;
    }

    public Object findRent(String name, String lastName){
        for (CarRentalImpl carReserved : rentedCars){
            if(carReserved.getfName().equals(name) && carReserved.getlName().equals(lastName) ) {

                return carReserved;
            }
        }
        return null;
    }
     public Object findReservation(String name, String lastName){
        for (CarReservationImpl carReserved : reservedCars){
            if(carReserved.getfName().equals(name) && carReserved.getlName().equals(lastName) ) {

                return carReserved;
            }
        }
        return null;
    }

    //Metody Reservation
    public void CreateReservedCars()
    {
        AddReservedCars(3,new Date (2022-1900,10-1,13),new Date(2022-1900,11-1,30), "Aleksandra", "Kruk");
    }

    public boolean AddReservedCars(int carId, Date dateFrom, Date dateTo, String fName, String lName){
        CarImpl car = (CarImpl) GetCar(carId);
        if(!car.isRented() || !car.isReserved()){
            car.setReserved(true);
            reservedCars.add(new CarReservationImpl(car, dateFrom, dateTo, fName, lName, Integer.parseInt(String.valueOf(reservedCars.size()))));
            return true;
        }
       return false;
    }

    public List<CarReservationImpl> GetReservedCars(){
        return reservedCars;
    }

    public void DeleteReservedCar(int id)
    {
        for (CarReservationImpl carReserved : reservedCars){
            if(carReserved.getCar().getId() == id) {
                reservedCars.remove(carReserved);
                carReserved.getCar().setReserved(false);
                return;
            }
        }
    }



}
