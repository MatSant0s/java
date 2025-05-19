package com.tcs.reto.exceptions;

import com.tcs.reto.bindings.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ApiResponse response = ApiResponse.builder()
                .code(400)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNotFound(NoSuchElementException ex) {
        ApiResponse response = ApiResponse.builder()
                .code(404)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        ApiResponse response = ApiResponse.builder()
                .code(500)
                .message("Ocurrió un error inesperado")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
