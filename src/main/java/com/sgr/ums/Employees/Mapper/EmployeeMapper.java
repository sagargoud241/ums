package com.sgr.ums.Employees.Mapper;

import com.sgr.ums.Employees.Model.Employee;
import com.sgr.ums.Employees.Model.EmployeeRequest;
import com.sgr.ums.Employees.Model.UpdateEmployeeRequest;

public class EmployeeMapper {

    public static Employee addEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employee.setAge(request.getAge());
        return employee;
    }

    public static Employee UpdateEmployee(Employee employee, UpdateEmployeeRequest request) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(request.getDepartment());
        employee.setAge(request.getAge());
        return employee;
    }
}
