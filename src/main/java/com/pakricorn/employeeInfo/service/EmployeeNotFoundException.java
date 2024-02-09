package com.pakricorn.employeeInfo.service;

public class EmployeeNotFoundException extends Throwable {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
