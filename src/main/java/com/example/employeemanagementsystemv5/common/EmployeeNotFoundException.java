package com.example.employeemanagementsystemv5.common;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException() {}

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
