package com.example.logistics.service.util;

import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import com.example.logistics.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for storage methods complains car and driver
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Car
 * @see Driver
 */
@Slf4j
@Component
public class CarAndDriverUtil {
    /**
     * Field for storage static amount max car for driver
     */
    private static Integer maxCarForDriver;

    /**
     * Field for storage non-static amount max car for driver
     */
    @Value("${logistics.MaxCarForDriver}")
    private Integer max;

    /**
     * Method for compliance Car and Driver models
     *
     * @param driver driver model
     * @param car car model
     * @return set string with error
     */
    public static Set<String> checkDriverAndCarForCompliance(Driver driver,
                                                             Car car) {
        Set<String> errors = new HashSet<>();
        if (!driver.getDriversLicense().getCategories().contains(car.getCategory())) {
            log.info("Checking necessary category of driver");
            errors.add("Driver with id " + driver.getId() + " doesn't have the necessary category");
        }
        if (driver.getCars().size() >= maxCarForDriver) {
            log.info("Checking amount cars of driver");
            errors.add("The number of cars for one driver is exceeded. Necessary amount = " + maxCarForDriver
                    + ".Current amount = " + driver.getCars().size());
        }
        if (driver.getDriversLicense().getDateOfExpired().isBefore(LocalDate.now())) {
            log.info("Checking date of expired license of driver");
            errors.add("License expired for driver with id =" + driver.getId());
        }
        return errors;
    }

    @PostConstruct
    void enableMaxAmountCar() {
        maxCarForDriver = max;
    }
}
