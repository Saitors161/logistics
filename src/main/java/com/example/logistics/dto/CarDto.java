package com.example.logistics.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Class for storage data for model Car
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see com.example.logistics.model.Car
 */
@Data
@Builder
public class CarDto {
    /**
     * Field for storage car id
     */
    private Integer id;
    /**
     * Field for storage car category
     */
    private String category;
    /**
     * Field for storage car number
     */
    private String number;
}
