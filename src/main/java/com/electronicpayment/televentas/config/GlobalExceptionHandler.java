package com.electronicpayment.televentas.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.electronicpayment.televentas.shared.exception.ExceptionResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponseDto> handleResponseException(ResponseStatusException ex) {

        return ResponseEntity.status(ex.getStatusCode()).body(new ExceptionResponseDto(ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException(Exception ex) {
        log.error("Error inesperado: ", ex);

        return ResponseEntity.internalServerError().body(new ExceptionResponseDto("Error inesperado, por favor comunicarse con el administrador del sistema."));
    }
}
