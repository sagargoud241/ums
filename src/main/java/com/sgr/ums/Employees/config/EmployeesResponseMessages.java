package com.sgr.ums.Employees.config;

public enum EmployeesResponseMessages {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    DUPLICATE_DATA("Duplicate data!"),
    REMOVED_SUCCESSFULLY("REMOVED_SUCCESSFULLY"),
    // exception cases
    REQUEST_TERMINATED("Request Terminated."),
    // data not available
    NOT_AVAILABLE("Not available."),
    VALIDATION_FAILED("Invalid Process!");

    private final String name;

    EmployeesResponseMessages(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

