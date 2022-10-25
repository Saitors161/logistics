package com.example.logistics.error;

import lombok.Builder;
import lombok.Data;

/**
 * Class custom exception EntityInLogisticAppNotFoundException
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see RuntimeException
 */
@Data
@Builder
public class EntityInLogisticAppNotFoundException extends RuntimeException {
    /**
     * Field for storage entity name
     */
    private String entityName;
    /**
     * Field for storage name of parameter
     */
    private String parameter;
    /**
     * Field for storage value of parameter
     */
    private String value;

    public EntityInLogisticAppNotFoundException(String message) {
        super(message);
    }

    public EntityInLogisticAppNotFoundException(String entityName, String parameter, String value) {
        super(entityName + " with " + parameter + " = " + value + " not found");
    }

    public EntityInLogisticAppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
