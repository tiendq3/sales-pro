package com.example.quanlybanhang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalTranslateException {

    public ResponseEntity<ErrorResponse> create(HttpStatus httpStatus, String reason, String path) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, reason, path);
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException e, WebRequest request) {
        return create(HttpStatus.NOT_FOUND, e.getMessage(), request.getContextPath());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlerValidUserName(BadRequestException e, WebRequest request) {
        return create(HttpStatus.BAD_REQUEST, e.getMessage(), request.getContextPath());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handlerException(Exception e, WebRequest request) {
        String error = e.getMessage();
        try {
            FileWriter fileWriter = new FileWriter("ExceptionRepo.txt", true);
            fileWriter.write("\n" + error);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getContextPath());
    }
}