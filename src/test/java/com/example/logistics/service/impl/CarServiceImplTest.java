package com.example.logistics.service.impl;

import com.example.logistics.enums.DriverCategory;
import com.example.logistics.error.EntityInLogisticAppNotFoundException;
import com.example.logistics.model.Car;
import com.example.logistics.repository.CarRepository;
import com.example.logistics.service.CarService;
import com.example.logistics.service.util.LogisticValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class CarServiceImplTest {

    private CarRepository carRepositoryMock = Mockito.mock(CarRepository.class);
    private LogisticValidator validatorMock = Mockito.mock(LogisticValidator.class);

    @Test
    public void updateCarExistTest() {
        Car carBefore = Car.builder()
                .id(1)
                .category(DriverCategory.A)
                .number("dksfjheu8282")
                .build();
        Mockito.when(carRepositoryMock.existsById(1)).thenReturn(true);
        Mockito.when(carRepositoryMock.save(carBefore)).thenReturn(carBefore);
        CarService carService = new CarServiceImpl(carRepositoryMock, validatorMock);
        Car car = carService.updateCar(carBefore);
        verify(validatorMock, times(1)).validatedObject(carBefore);
        verify(carRepositoryMock, times(1)).existsById(carBefore.getId());
        verify(carRepositoryMock, times(1)).save(carBefore);
        Assertions.assertEquals(car, carBefore);
    }

    @Test
    public void updateCarNotExistTest() {
        Car carBefore = Car.builder()
                .id(1)
                .category(DriverCategory.A)
                .number("dksfjheu8282")
                .build();
        Mockito.when(carRepositoryMock.existsById(1)).thenReturn(false);
        CarService carService = new CarServiceImpl(carRepositoryMock, validatorMock);
        Exception exception = assertThrows(EntityInLogisticAppNotFoundException.class, () -> {
            Car cars = carService.updateCar(carBefore);
        });
        verify(validatorMock, times(1)).validatedObject(carBefore);
        verify(carRepositoryMock, times(1)).existsById(carBefore.getId());
        verify(carRepositoryMock, times(0)).save(carBefore);

        String expectedMessage = "Car with id = 1 not found";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    //todo: add some util tests
}
