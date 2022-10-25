package com.example.logistics.service.impl;

import com.example.logistics.error.EntityInLogisticAppNotFoundException;
import com.example.logistics.error.IncorrectDataForOperationException;
import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import com.example.logistics.repository.DriverRepository;
import com.example.logistics.service.CarService;
import com.example.logistics.service.DriverService;
import com.example.logistics.service.util.CarAndDriverUtil;
import com.example.logistics.service.util.LogisticValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Class implements interface DriverService. Storage main business logic for model Driver.
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
public class DriverServiceImpl implements DriverService {
    /**
     * Field for storage driver repository
     */
    private final DriverRepository driverRepository;

    /**
     * Field for storage car service
     */
    private final CarService carService;

    /**
     * Field for storage validator of logistic
     */
    private final LogisticValidator validator;

    /**
     * Method for save new Driver
     *
     * @param driver Driver (Model)
     * @see Driver
     */
    @Override
    public Driver saveDriver(Driver driver) {
        validator.validatedObject(driver);
        log.info("Try save driver: " + driver);
        driver = driverRepository.save(driver);
        log.info("Driver saved : " + driver);
        return driver;
    }

    /**
     * Method for get Driver by Id
     *
     * @param id driver id
     */
    public Driver getDriverById(Integer id) {
        log.info("Try get driver by id " + id);
        Driver driver = driverRepository.findById(id).orElseThrow(() ->
                EntityInLogisticAppNotFoundException.builder()
                        .entityName("Driver")
                        .parameter("id")
                        .value(id.toString())
                        .build()
        );
        log.info("Driver was found : " + driver);
        return driver;
    }

    /**
     * Method for get all Drivers
     */
    @Override
    public List<Driver> getAllDrivers() {
        log.info("Try get all drivers");
        List<Driver> drivers = driverRepository.findAll();
        log.info(drivers.size() + " drivers in total");
        return drivers;
    }
    /**
     * Method for update Driver
     *
     * @param driver Driver (Model)
     * @see Driver
     */
    @Override
    public Driver updateDriver(Driver driver) {
        validator.validatedObject(driver);
        log.info("Check driver by id " + driver.getId());
        if (driverRepository.existsById(driver.getId())) {
            log.info("Driver with id " + driver.getId() + " exists");
            return driverRepository.save(driver);
        } else {
            log.info("Driver by id " + driver.getId() + " not found");
            throw EntityInLogisticAppNotFoundException.builder()
                    .entityName("Driver")
                    .parameter("id")
                    .value(driver.getId().toString())
                    .build();
        }
    }

    /**
     * Method for delete Driver by id
     *
     * @param id driver id
     */
    @Override
    public void deleteDriverById(Integer id) {
        log.info("Try delete driver by id " + id);
        driverRepository.deleteById(id);
        log.info("Driver with id " + id + " deleted");
    }

    /**
     * Method for add exist Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    @Override
    public void addCarForDriver(Integer driverId, Integer carId) {
        log.info("Try add car with id " + carId + " for driver with id " + driverId);
        Driver driver = getDriverById(driverId);
        Car car = carService.getCarById(carId);
        Set<String> errors = CarAndDriverUtil.checkDriverAndCarForCompliance(driver, car);
        if (errors.isEmpty()) {
            log.info("Driver can add new car");
            driver.getCars().add(car);
            driverRepository.save(driver);
        } else {
            log.info("Driver can't add new car");
            throw IncorrectDataForOperationException.builder()
                    .errors(errors).build();
        }
    }

    /**
     * Method for remove Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    @Override
    public void removeCarFromDriver(Integer driverId, Integer carId) {
        Driver driver = getDriverById(driverId);
        int carAmountStart = driver.getCars().size();
        log.info("Try delete car from driver. Car amount = " + carAmountStart);
        driver.getCars().removeIf(car -> car.getId().equals(carId));
        if (driver.getCars().size() != carAmountStart) {
            log.info("Car was deleted from driver with id " + driverId);
            driverRepository.save(driver);
        }
    }

    /**
     * Method for get all Cars for Driver
     *
     * @param driverId driver id
     */
    @Override
    public List<Car> getAllDriversCar(Integer driverId) {
        Driver driver = getDriverById(driverId);
        if (driver == null) {
            throw EntityInLogisticAppNotFoundException.builder()
                    .entityName("Driver")
                    .parameter("id")
                    .value(driverId.toString())
                    .build();
        }
        return driver.getCars();           //todo: maybe need refactor for better performance
    }
}
