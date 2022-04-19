package ws;


import models.Car;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class AppController {

    private List<Car> cars = new ArrayList<>();
    //inicjalizacja Seedera
    public AppController() throws Exception {
        CreateCars();
    }

    // Metody Car
   private void CreateCars() throws Exception {
        AddCar("Mitshubishi", "Colt", 2004, 2000000,9230);
        AddCar("Citroen", "c4", 2012, 4000000,8230);
        AddCar("Mitshubishi", "Colt3", 2004, 2000000,6444);
    }

//POST
   public void AddCar(String carBrand, String carModel, int productionYear, int mileage, int price) throws Exception {
        Car newCar = new Car(Integer.parseInt(String.valueOf(cars.size())), carBrand, carModel, productionYear, mileage,price);
        cars.add(newCar);
    }
//helper mozna wykorzystac do edit car (PUT)
    public Car FindCar(int id)
    {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }
//GET
    public Car GetCar(int id) throws Exception
    {
        return FindCar(id);
    }
//GET ALL
    public List<Car> GetCars() {
        return cars;
    }


    //Metody Hire



}
