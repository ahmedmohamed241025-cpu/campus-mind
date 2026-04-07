package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
//>>>>>>>>>>>>>>>>>>>>>>>>>>>Handling (معالجة الأخطاء)>>>>>>>>>>>>>>>>>>>>>>>>>>

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            return errors;
        }


        @ExceptionHandler(RuntimeException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public Map<String, Object> handleRuntimeException(RuntimeException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("message", ex.getMessage());
            error.put("status", HttpStatus.NOT_FOUND.value());
            return error;
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Map<String, Object> handleGenericException(Exception ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("message", "An unexpected error occurred: " + ex.getMessage());
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return error;
        }
    }

