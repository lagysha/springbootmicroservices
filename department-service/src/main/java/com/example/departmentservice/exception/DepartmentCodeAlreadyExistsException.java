package com.example.departmentservice.exception;

public class DepartmentCodeAlreadyExistsException extends RuntimeException{
    public DepartmentCodeAlreadyExistsException(String message) {
        super(message);
    }
}
