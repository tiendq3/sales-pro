package com.example.quanlybanhang.exception;

import org.springframework.http.HttpStatus;
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

// thay vi controller trả về client thì exceptionhandler trả về client khi xảy ra ngoại lệ
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class) // bắt loại ngoại lệ
    @ResponseStatus(HttpStatus.NOT_FOUND)  // trả vễ http status code cho client
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest request) {
        String error = ex.getMessage();
        //...... xử lý sau đó-- lưu vào file hoặc db
        try {
            FileWriter fileWriter = new FileWriter("ExceptionRepo.txt", true);
            fileWriter.write("\n" + error);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("k tim thay file");
        }

        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidUserNameEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerValidUserName(ValidUserNameEx ex, WebRequest request){
        String error = ex.getMessage();
        //...... xử lý sau đó-- lưu vào file hoặc db
        try {
            FileWriter fileWriter = new FileWriter("ExceptionRepo.txt", true);
            fileWriter.write("\n" + error);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("k tim thay file");
        }

        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    // để bắt dc tất cả những exception còn lại chưa phát hiện phát hiện
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handlerException(Exception ex, WebRequest request) throws IOException {
//        // để fix lỗi này, chúng ta sẽ lưu lỗi vào file
//        String error = ex.getMessage();
//        //...... xử lý sau đó-- lưu vào file hoặc db
//        try {
//            FileWriter fileWriter = new FileWriter("ExceptionRepo.txt", true);
//            fileWriter.write("\n" + error);
//            fileWriter.close();
//        } catch (IOException e) {
//            System.out.println("k tim thay file");
//        }
//
//        // sẽ trả về client như thế này
//        return new ErrorResponse("loi dac biet", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}