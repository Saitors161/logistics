package com.example.logistics.error;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class for handling custom exception in app
 *
 * @author Tsirdava Mikhail <>saitors161@mail.ru</>
 * @version 1.0
 * @see ResponseEntityExceptionHandler
 */
@RestControllerAdvice
public class ApiLogisticExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Method for handling EntityInLogisticAppNotFoundException
     *
     * @param e EntityInLogisticAppNotFoundException object
     */
    @ExceptionHandler(value = {EntityInLogisticAppNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Entity not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))
                    })
    })
    public ResponseEntity<Object> handleApiEntityNotFoundException(EntityInLogisticAppNotFoundException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Method for handling IllegalArgumentException
     *
     * @param e IllegalArgumentException object
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Enum not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))
                    })
    })
    public ResponseEntity<Object> handleApiEnumNotFoundException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Method for handling IncorrectDataForOperationException
     *
     * @param e IncorrectDataForOperationException object
     */
    @ExceptionHandler(value = {IncorrectDataForOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect data for operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiException.class))
                    })
    })
    public ResponseEntity<Object> handleApiIncorrectDataForOperationException(IncorrectDataForOperationException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
