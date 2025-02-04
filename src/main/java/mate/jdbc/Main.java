package mate.jdbc;

import java.util.ArrayList;
import java.util.List;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Car;
import mate.jdbc.model.Driver;
import mate.jdbc.model.Manufacturer;
import mate.jdbc.service.CarService;
import mate.jdbc.service.DriverService;
import mate.jdbc.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");
    private static final DriverService driverService
            = (DriverService) injector.getInstance(DriverService.class);
    private static final ManufacturerService manufacturerService
            = (ManufacturerService) injector.getInstance(ManufacturerService.class);
    private static final CarService carService
            = (CarService) injector.getInstance(CarService.class);

    public static void main(String[] args) {
        Manufacturer mercedes = new Manufacturer("Mercedes", "German");
        Manufacturer toyota = new Manufacturer("Lamborghini", "Italy");
        manufacturerService.create(mercedes);
        manufacturerService.create(toyota);
        manufacturerService.getAll().forEach(System.out::println);

        Driver firstDriver = new Driver("Vladyslav", "57fya132");
        driverService.create(firstDriver);
        Driver secondDriver = new Driver("Marina", "1212ifo");
        driverService.create(secondDriver);
        driverService.getAll().forEach(System.out::println);

        List<Driver> drivers = new ArrayList<>();
        drivers.add(firstDriver);
        Car car2 = new Car("c-hr", toyota, drivers);
        drivers.add(secondDriver);
        Car car = new Car("GLE", mercedes, drivers);
        carService.create(car);
        carService.create(car2);
        System.out.println(carService.get(car2.getId()));
        carService.removeDriverFromCar(firstDriver, car);

        toyota.setName("Lexus");
        manufacturerService.update(toyota);
        manufacturerService.getAll().forEach(System.out::println);

        System.out.println(carService.getAllByDriver(firstDriver.getId()));
        carService.delete(car.getId());
        System.out.println(manufacturerService.get(mercedes.getId()));
        driverService.getAll().forEach(System.out::println);
    }
}
