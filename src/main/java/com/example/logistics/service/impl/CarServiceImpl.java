package com.example.logistics.service.impl;

import com.example.logistics.error.EntityInLogisticAppNotFoundException;
import com.example.logistics.model.Car;
import com.example.logistics.repository.CarRepository;
import com.example.logistics.service.CarService;
import com.example.logistics.service.util.LogisticValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;


/**
 * Class implements interface CarService. Storage main business logic for model Car.
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Car
 * @see CarService
 * @see LogisticValidator
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    /**
     * Field for storage car repository
     */
    private final CarRepository carRepository;
    /**
     * Field for storage validator of logistic
     */
    private final LogisticValidator validator;

    /**
     * Method for get Car by id from repository
     *
     * @param id car id
     */
    @Override
    public Car getCarById(Integer id) {
        log.info("Try get car by id" + id);
        return carRepository.findById(id).orElseThrow(() -> EntityInLogisticAppNotFoundException.builder()
                .entityName("Car")
                .parameter("id")
                .value(id.toString())
                .build());
    }

    /**
     * Method for get all Cars
     */
    @Override
    public List<Car> getAllCars() {
        log.info("Try get all car");
        List<Car> carList = carRepository.findAll();
        log.info(carList.size() + " cars in total");
        return carList;
    }

    /**
     * Method for delete Car by id from repository
     *
     * @param id car id
     */
    @Override
    public void deleteCarById(Integer id) {
        log.info("Try delete car by id" + id);
        carRepository.deleteById(id);
        log.info("Car with id " + id + " deleted");
    }

    /**
     * Method for update Car
     *
     * @param car Car (Model)
     */
    @Override
    public Car updateCar(Car car) {
        validator.validatedObject(car);
        log.info("Check car exist by id " + car.getId());
        if (carRepository.existsById(car.getId())) {
            log.info("Car exist with id " + car.getId());
            return carRepository.save(car);
        } else {
            throw EntityInLogisticAppNotFoundException.builder()
                    .entityName("Car")
                    .parameter("id")
                    .value(car.getId().toString())
                    .build();
        }
    }

    /**
     * Method for save new Car
     *
     * @param car Car (Model)
     */
    public Car saveCar(@Valid Car car) {
        validator.validatedObject(car);
        log.info("Try save car " + car);
        car = carRepository.save(car);
        log.info("Car saved " + car);
        return car;
    }
}
