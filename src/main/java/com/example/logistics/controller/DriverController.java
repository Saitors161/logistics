package com.example.logistics.controller;

import com.example.logistics.dto.CarDto;
import com.example.logistics.dto.DriverDto;
import com.example.logistics.mapper.CarListMapper;
import com.example.logistics.mapper.DriverListMapper;
import com.example.logistics.mapper.DriverMapper;
import com.example.logistics.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class for access to driver service from API
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
@Tag(name = "Drivers", description = "CRUD for drivers")
public class DriverController {
    /**
     * Field for storage driver service
     */
    private final DriverService driverService;
    /**
     * Field for storage driver mapper
     */
    private final DriverMapper driverMapper;
    /**
     * Field for storage driver list mapper
     */
    private final DriverListMapper driverListMapper;
    /**
     * Field for storage car list mapper
     */
    private final CarListMapper carListMapper;

    /**
     * Method for get Driver by Id
     *
     * @param id driver id
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get driver by id", tags = "Drivers")
    DriverDto getDriverById(@PathVariable Integer id) {
        return driverMapper.toDto(driverService.getDriverById(id));
    }

    /**
     * Method for get all Drivers
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all drivers", tags = "Drivers")
    List<DriverDto> getAllDrivers() {
        return driverListMapper.toDto(driverService.getAllDrivers());
    }

    /**
     * Method for save new Driver
     *
     * @param driverDto this DTO storages data for new Driver
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create driver", tags = "Drivers")
    DriverDto saveDriver(@RequestBody DriverDto driverDto) {
        return driverMapper.toDto(driverService.saveDriver(driverMapper.toModel(driverDto)));
    }

    /**
     * Method for update Driver
     *
     * @param driverDto this DTO storages data for update Driver
     */
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "update driver", tags = "Drivers")
    DriverDto updateDriver(@RequestBody DriverDto driverDto) {
        return driverMapper.toDto(driverService.updateDriver(driverMapper.toModel(driverDto)));
    }

    /**
     * Method for delete Driver by id
     *
     * @param id driver id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete driver by id", tags = "Drivers")
    void deleteDriverById(@PathVariable Integer id) {
        driverService.deleteDriverById(id);
    }

    /**
     * Method for add exist Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    @PostMapping("/{driverId}/cars/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "add car for driver", tags = "Drivers")
    void addCarForDriver(@PathVariable Integer driverId, @PathVariable Integer carId) {
        driverService.addCarForDriver(driverId, carId);
    }

    /**
     * Method for remove Car for Driver
     *
     * @param driverId driver id
     * @param carId    car id
     */
    @DeleteMapping("/{driverId}/cars/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "remove car from driver", tags = "Drivers")
    void removeCarFromDriver(@PathVariable Integer driverId, @PathVariable Integer carId) {
        driverService.removeCarFromDriver(driverId, carId);
    }

    /**
     * Method for get all Cars for Driver
     *
     * @param driverId driver id
     */
    @GetMapping("/{driverId}/cars")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "get all cars by drivers id", tags = "Drivers")
    List<CarDto> getAllDriversCar(@PathVariable Integer driverId) {
        return carListMapper.toDto(driverService.getAllDriversCar(driverId));
    }
}
