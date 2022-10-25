package com.example.logistics.service;

import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;

import java.util.List;

/**
 * Interface for describe behavior Driver (Model)
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Driver
 */
public interface DriverService {

    /**
     * Method for save new Driver
     *
     * @param driver Driver (Model)
     * @see Driver
     */
    Driver saveDriver(Driver driver);

    /**
     * Method for get all Drivers
     */
    List<Driver> getAllDrivers();

    /**
     * Method for update Driver
     *
     * @param driver Driver (Model)
     * @see Driver
     */
    Driver updateDriver(Driver driver);

    /**
     * Method for delete Driver by id
     *
     * @param id driver id
     */
    void deleteDriverById(Integer id);

    /**
     * Method for add exist Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    void addCarForDriver(Integer driverId, Integer carId);

    /**
     * Method for remove Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    void removeCarFromDriver(Integer driverId, Integer carId);

    /**
     * Method for get all Cars for Driver
     *
     * @param driverId driver id
     */
    List<Car> getAllDriversCar(Integer driverId);

    /**
     * Method for get Driver by id
     *
     * @param id driver id
     */
    Driver getDriverById(Integer id);
}
