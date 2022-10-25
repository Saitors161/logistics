package com.example.logistics.service.util;

import com.example.logistics.enums.DriverCategory;
import com.example.logistics.error.IncorrectDataForOperationException;
import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import com.example.logistics.service.util.LogisticValidator;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;

import static org.junit.jupiter.api.Assertions.*;

public class LogisticValidatorTest {
    private final LogisticValidator validator = new LogisticValidator(Validation.buildDefaultValidatorFactory().getValidator());

    @Test
    public void testCarWithoutData() {
        Car car = new Car();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(car);
        });
        String expectedMessage = "Incorrect data for operation : [Cars category should not be empty, Cars number should not be empty]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testCarInCorrectData() {
        Car car = Car.builder()
                .number("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssssssssssssssssssssssssssgggggggggggggggggggggggggggggggggggg" +
                        "gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggssssssssssssssssssssssssssssssss")
                .build();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(car);
        });
        String expectedMessage = "Incorrect data for operation : [Cars category should not be empty, Max size for car number = 100 characters]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testCarHalfCorrectData() {
        Car car = Car.builder()
                .category(DriverCategory.B)
                .build();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(car);
        });
        String expectedMessage = "Incorrect data for operation : [Cars number should not be empty]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testCarCorrectData() {
        Car car = Car.builder()
                .category(DriverCategory.B)
                .number("123ooo")
                .build();
        assertDoesNotThrow(() ->
                validator.validatedObject(car)
        );
    }


    @Test
    public void testDriverWithoutData() {
        Driver driver = new Driver();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(driver);
        });
        String expectedMessage = "Incorrect data for operation : [Drivers first name should not be empty, Drivers last name should not be empty]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testDriverHalfCorrectData() {
        Driver driver = Driver.builder()
                .lastName("Kong")
                .build();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(driver);
        });
        String expectedMessage = "Incorrect data for operation : [Drivers first name should not be empty]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testDriverInCorrectData() {
        Driver driver = Driver.builder()
                .lastName("Kossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssng")
                .firstName("Kossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssng")
                .build();
        Exception exception = assertThrows(IncorrectDataForOperationException.class, () -> {
            validator.validatedObject(driver);
        });
        String expectedMessage = "Incorrect data for operation : [Max size for drivers last name = 100 characters," +
                " Max size for drivers first name = 100 characters]";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }


    @Test
    public void testDriverCorrectData() {
        Driver driver = Driver.builder()
                .firstName("Lonk")
                .lastName("Pong")
                .build();
        assertDoesNotThrow(() -> {
            validator.validatedObject(driver);
        });
    }

}
