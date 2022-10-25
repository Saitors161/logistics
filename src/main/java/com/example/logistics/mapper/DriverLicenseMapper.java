package com.example.logistics.mapper;

import com.example.logistics.dto.DriversLicenseDto;
import com.example.logistics.model.DriversLicense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Interface for mapping DriverLicenseDto and DriverLicense(Model) by using MapStruct
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see DriversLicense
 * @see DriversLicenseDto
 */
@Mapper(componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.IGNORE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DriverLicenseMapper {
    /**
     * Method for mapping Dto to Model
     *
     * @param driversLicenseDto driversLicenseDto
     */
    DriversLicense toModel (DriversLicenseDto driversLicenseDto);
    /**
     * Method for mapping Model to Dto
     *
     * @param driversLicense driversLicense (Model)
     */
    DriversLicenseDto toDto (DriversLicense driversLicense);

}
