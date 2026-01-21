package org.curso.spring.besttravel.api.controller.error;

import org.curso.spring.besttravel.domain.exceptions.InvalidPriceException;
import org.curso.spring.besttravel.domain.exceptions.InvalidPriceRangeException;
import org.curso.spring.besttravel.domain.exceptions.InvalidRangeException;
import org.curso.spring.besttravel.domain.exceptions.ResourceNotFoundException;
import org.curso.spring.besttravel.utils.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.curso.spring.besttravel.api.models.dto.response.ErrorResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                ErrorCode.RESOURCE_NOT_FOUND.name(),
                ex.getMessage());
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPrice(InvalidPriceException ex) {
        return buildResponse(
               HttpStatus.BAD_REQUEST,
                ErrorCode.INVALID_PRICE.name(),
               ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPriceRange(InvalidPriceRangeException ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.INVALID_PRICE_RANGE.name(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(InvalidRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRange(InvalidRangeException ex) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.INVALID_RANGE.name(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_ERROR.name(),
                errors
        );
    }




    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String error,
            Object message
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
