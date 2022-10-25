package com.example.logistics.service.impl;

import com.example.logistics.error.EntityInLogisticAppNotFoundException;
import com.example.logistics.model.Driver;
import com.example.logistics.repository.DriverRepository;
import com.example.logistics.service.CarService;
import com.example.logistics.service.DriverService;
import com.example.logistics.service.util.ITServiceUtil;
import com.example.logistics.service.util.LogisticValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DriverServiceImplTest {
    private DriverRepository driverRepositoryMock = Mockito.mock(DriverRepository.class);
    private CarService carServiceMock = Mockito.mock(CarService.class);
    private LogisticValidator validatorMock = Mockito.mock(LogisticValidator.class);

    @Test
    public void updateDriverExistTest() {
        Driver driverBefore = ITServiceUtil.getTestDriver();
        driverBefore.setId(1);
        Mockito.when(driverRepositoryMock.existsById(driverBefore.getId())).thenReturn(true);
        Mockito.when(driverRepositoryMock.save(driverBefore)).thenReturn(driverBefore);
        DriverService driverService = new DriverServiceImpl(driverRepositoryMock, carServiceMock, validatorMock);
        Driver driver = driverService.updateDriver(driverBefore);
        verify(validatorMock, times(1)).validatedObject(driverBefore);
        verify(driverRepositoryMock, times(1)).existsById(driverBefore.getId());
        verify(driverRepositoryMock, times(1)).save(driverBefore);
        Assertions.assertEquals(driver, driverBefore);
    }

    @Test
    public void updateDriverNotExistTest() {
        Driver driverBefore = ITServiceUtil.getTestDriver();
        driverBefore.setId(1);
        Mockito.when(driverRepositoryMock.existsById(driverBefore.getId())).thenReturn(false);
        DriverService driverService = new DriverServiceImpl(driverRepositoryMock, carServiceMock, validatorMock);
        Exception exception = assertThrows(EntityInLogisticAppNotFoundException.class, () -> {
            driverService.updateDriver(driverBefore);
        });
        verify(validatorMock, times(1)).validatedObject(driverBefore);
        verify(driverRepositoryMock, times(1)).existsById(driverBefore.getId());
        verify(driverRepositoryMock, times(0)).save(driverBefore);

        String expectedMessage = "Driver with id = 1 not found";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
    //todo: add some util tests
}
