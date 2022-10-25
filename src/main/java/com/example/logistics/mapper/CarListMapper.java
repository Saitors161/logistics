package com.example.logistics.mapper;

import com.example.logistics.dto.CarDto;
import com.example.logistics.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interface for mapping list CarDto and list Car(Model) by using MapStruct
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see CarDto
 * @see Car
 */
@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface CarListMapper {

    /**
     * Method for mapping list Dto to list Model
     *
     * @param carDtoList list CarDto
     */
    List<Car> toModel(List<CarDto> carDtoList);

    /**
     * Method for mapping list Model to list Dto
     *
     * @param cars list Cars
     */
    List<CarDto> toDto(List<Car> cars);
}
