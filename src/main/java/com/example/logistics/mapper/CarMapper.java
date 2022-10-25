package com.example.logistics.mapper;

import com.example.logistics.dto.CarDto;
import com.example.logistics.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * Interface for mapping CarDto and Car(Model) by using MapStruct
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see CarDto
 * @see Car
 */
@Mapper(componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    /**
     * Method for mapping Model to Dto
     *
     * @param car Car (model)
     */
    CarDto toDto(Car car);

    /**
     * Method for mapping Dto to Model
     *
     * @param carDto CarDto
     */
    Car toModel(CarDto carDto);
}
