package com.ltc.companymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DepartmentNotFoundException.class)
    public ErrorResponseDto departmentExceptionHandler(DepartmentNotFoundException ex ) {

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .build();

        return errorResponseDto;
    }

        @ExceptionHandler(EmployeeNotFoundException.class)
        public ErrorResponseDto employeeExceptionHandler(EmployeeNotFoundException ex ){

            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message(ex.getMessage())
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .timestamp(LocalDateTime.now())
                    .build();

            return errorResponse;
        }

//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setMessage(ex.getMessage());
//        errorResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        errorResponseDto.setTimestamp(LocalDateTime.now());
//        return errorResponseDto;
//

    }



