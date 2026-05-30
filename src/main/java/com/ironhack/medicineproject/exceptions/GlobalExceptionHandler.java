package com.ironhack.medicineproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.naming.InvalidNameException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSearchNotFoundException(
            SearchNotFoundException ex) {

        String formattedTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ErrorResponse errorResponse = new ErrorResponse(
                formattedTime, ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String errorMessage =
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

        String formattedTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return ResponseEntity.badRequest().body(
                new ErrorResponse(formattedTime, errorMessage
                )
        );
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidName(InvalidNameException ex) {

        String formattedTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return ResponseEntity.badRequest().body(
                new ErrorResponse(
                        formattedTime,
                        ex.getMessage()
                )
        );
    }
}