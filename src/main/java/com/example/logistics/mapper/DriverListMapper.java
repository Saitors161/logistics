package com.example.logistics.mapper;

import com.example.logistics.dto.DriverDto;
import com.example.logistics.dto.DriversLicenseDto;
import com.example.logistics.model.Driver;
import com.example.logistics.model.DriversLicense;
import org.mapstruct.Mapper;

import java.util.List;
/**
 * Interface for mapping list DriverDto and list Driver(Model) by using MapStruct
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Driver
 * @see DriverDto
 */
@Mapper(componentModel = "spring",uses = DriverMapper.class)
public interface DriverListMapper {
    /**
     * Method for mapping list Dto to list Model
     *
     * @param driversDto list driverDto
     */
    List<Driver> toModel(List<DriverDto> driversDto);

    /**
     * Method for mapping Model to Dto
     *
     * @param drivers drivers (Model)
     */
    List<DriverDto> toDto(List<Driver> drivers);
}
