package com.example.logistics.error;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * Class custom exception IncorrectDataForOperationException
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see RuntimeException
 */
@Data
@Builder
public class IncorrectDataForOperationException extends RuntimeException{
    /**
     * Field for storage errors
     */
    private Set<String> errors;

    public IncorrectDataForOperationException(String message){
        super(message);
    }
    public IncorrectDataForOperationException(Set<String> errors){
        super("Incorrect data for operation : " + errors);
    }
    public IncorrectDataForOperationException(String message, Throwable cause){
        super(message,cause);
    }

}
