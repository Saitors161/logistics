package com.example.logistics.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Class for storage data for model Driver
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see com.example.logistics.model.Driver
 */
@Data
@Builder
public class DriverDto {
    /**
     * Field for storage driver id
     */
    private Integer id;
    /**
     * Field for storage driver first name
     */
    private String firstName;
    /**
     * Field for storage driver last name
     */
    private String lastName;
    /**
     * Field for storage data driver license
     */
    private DriversLicenseDto driversLicense;
}
