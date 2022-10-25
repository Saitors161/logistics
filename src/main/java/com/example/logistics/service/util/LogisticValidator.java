package com.example.logistics.service.util;

import com.example.logistics.error.IncorrectDataForOperationException;
import com.example.logistics.model.Car;
import com.example.logistics.model.Driver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Class for storage methods validation models
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see Validator
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LogisticValidator {
    /**
     * Field for storage validator (javax.validation.Validator)
     */
    private final Validator validator;
    /**
     * Method for validation models
     *
     * @param o object model
     */
    public void validatedObject(Object o) {
        log.info("Validate object " + o);
        Set<String> setErrors = validator.validate(o).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
        if(!setErrors.isEmpty()){
            log.info("The object " + o +" is not valid " );
            throw IncorrectDataForOperationException.builder().errors(setErrors).build();
        }
    }
}
