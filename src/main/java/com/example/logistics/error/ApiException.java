package com.example.logistics.error;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class for storage data custom exception
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 */
@Data
public class ApiException {
    /**
     * Field for storage error message
     */
    private String message;
    /**
     * Field for storage time of exception
     */
    private LocalDateTime localDateTime = LocalDateTime.now();

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException() {
    }
}
