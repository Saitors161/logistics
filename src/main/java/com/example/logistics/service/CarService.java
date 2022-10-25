package com.example.logistics.service;

import com.example.logistics.model.Car;

import java.util.List;

/**
 * Interface for describe behavior Car (Model)
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Car
 */
public interface CarService {

    Car saveCar(Car car);

    /**
     * Method for get Car by id from repository
     *
     * @param id id
     */
    Car getCarById(Integer id);

    /**
     * Method for get all Cars
     */
    List<Car> getAllCars();

    /**
     * Method for delete Car by id from repository
     *
     * @param id car id
     */
    void deleteCarById(Integer id);

    /**
     * Method for update Car
     *
     * @param car Car (Model)
     */
    Car updateCar(Car car);

}
