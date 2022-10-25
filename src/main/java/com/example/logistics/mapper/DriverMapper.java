package com.example.logistics.mapper;

import com.example.logistics.dto.DriverDto;
import com.example.logistics.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * Interface for mapping DriverDto and Driver(Model) by using MapStruct
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Driver
 * @see DriverDto
 */
@Mapper(componentModel = "spring"
        , uses = {DriverMapper.class, CarListMapper.class}
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DriverMapper {
    /**
     * Method for mapping Dto to Model
     *
     * @param driverDto driverDto
     */
    Driver toModel(DriverDto driverDto);
    /**
     * Method for mapping Model to Dto
     *
     * @param driver driverDto
     */
    DriverDto toDto(Driver driver);
}
