package com.example.logistics.dto;

import com.example.logistics.enums.DriverCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Class for storage data for model DriverLicense
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see com.example.logistics.model.DriversLicense
 */
@Data
@Builder
public class DriversLicenseDto {
    /**
     * Field for storage license id
     */
    private Integer id;
    /**
     * Field for storage license date of expired
     */
    private LocalDate dateOfExpired;
    /**
     * Field for storage drivers categories
     */
    private List<DriverCategory> categories;
}
