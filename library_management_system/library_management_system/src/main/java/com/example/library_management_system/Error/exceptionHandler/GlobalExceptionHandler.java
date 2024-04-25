package com.example.library_management_system.Error.exceptionHandler;


import com.example.library_management_system.Error.errorEntity.ApiResponseError;
import com.example.library_management_system.Error.exceptions.BookAlreadeBorrowedExcepotion;
import com.example.library_management_system.Error.exceptions.DeleteAssosiatedRecordsException;
import com.example.library_management_system.Error.exceptions.DuplicatedRecordException;
import com.example.library_management_system.Error.exceptions.RecordNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors= new ArrayList<String>();
        for (FieldError objectError : ex.getBindingResult().getFieldErrors()) {
            errors.add(objectError.getDefaultMessage());
        }
        for(ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(error.getDefaultMessage());
        }
        ApiResponseError error= new ApiResponseError(ex.toString(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException ex) {
        ApiResponseError error= new ApiResponseError(ex.getMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicatedRecordException.class)
    public ResponseEntity<?> handleDuplicatedRecordException(DuplicatedRecordException ex) {
        ApiResponseError error= new ApiResponseError(ex.getMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BookAlreadeBorrowedExcepotion.class)
    public ResponseEntity<?> handleBookAlreadyBorrowedException(BookAlreadeBorrowedExcepotion ex) {
        ApiResponseError error= new ApiResponseError(ex.getMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.IM_USED).body(error);
    }

    @ExceptionHandler(DeleteAssosiatedRecordsException.class)
    public ResponseEntity<?> handleDeleteAssosiatedRecordsException(DeleteAssosiatedRecordsException ex) {
        ApiResponseError error= new ApiResponseError(ex.getMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.IM_USED).body(error);
    }

}
