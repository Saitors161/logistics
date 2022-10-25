package com.example.logistics.controller;

import com.example.logistics.dto.CarDto;
import com.example.logistics.mapper.CarListMapper;
import com.example.logistics.mapper.CarMapper;
import com.example.logistics.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class for access to car service from API
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cars")
@Tag(name = "Cars", description = "CRUD for cars")
public class CarController {
    /**
     * Field for storage car service
     */
    private final CarService carService;
    /**
     * Field for storage car mapper
     */
    private final CarMapper carMapper;
    /**
     * Field for storage car list mapper
     */
    private final CarListMapper carListMapper;


    /**
     * Method for get Car by id
     *
     * @param id
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get car by id", tags = "Cars")
    CarDto getCarById(@PathVariable Integer id) {
        return carMapper.toDto(carService.getCarById(id));
    }

    /**
     * Method for get all Cars
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get car by id", tags = "Cars")
    List<CarDto> getAllCars() {
        return carListMapper.toDto(carService.getAllCars());
    }

    /**
     * Method for save new Car
     *
     * @param carDto this DTO storages data for new Car
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save car", tags = "Cars")
    CarDto saveCar(@RequestBody CarDto carDto) {
        return carMapper.toDto(carService.saveCar(carMapper.toModel(carDto)));
    }

    /**
     * Method for update Car
     *
     * @param carDto this DTO storages data for update Car
     */
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "update car by id", tags = "Cars")
    CarDto updateCar(@Valid @RequestBody CarDto carDto) {
        return carMapper.toDto(carService.updateCar(carMapper.toModel(carDto)));
    }

    /**
     * Method for delete Car by id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "delete car by id", tags = "Cars")
    void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }


}
