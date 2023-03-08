package com.example.departmentservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DepartmentCodeAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleDepartmentCodeAlreadyExistsException(DepartmentCodeAlreadyExistsException e, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                webRequest.getDescription(false),
                e.getMessage(),
                "DEPARTMENT_CODE_ALREADY_EXISTS");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                webRequest.getDescription(false),
                e.getMessage(),
                "DEPARTMENT_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception e,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                webRequest.getDescription(false),
                e.getMessage(),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {
        HashMap<String,String> map = new HashMap<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        errors.forEach((error ->{
            String fieldName= ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            map.put(fieldName,message);
        }));

        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}
