package com.sgr.ums.Employees.config;

public enum EmployeesStatusCode {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED");

    private final String name;

    EmployeesStatusCode(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

    public static String getAllCodesValues() {
        return "[ " + ACTIVE + "," + INACTIVE + "," + DELETED + " ]";
    }
}
