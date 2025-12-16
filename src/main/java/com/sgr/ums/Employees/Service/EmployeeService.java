package com.sgr.ums.Employees.Service;

import com.sgr.ums.Employees.Model.DeleteEmployeeRequest;
import com.sgr.ums.Employees.Model.EmployeeRequest;
import com.sgr.ums.Employees.Model.EmployeeResponse;
import com.sgr.ums.Employees.Model.UpdateEmployeeRequest;

public interface EmployeeService {
    EmployeeResponse add(EmployeeRequest request, String actionUser);

    EmployeeResponse list(String actionUser);

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse UpdateEmployeeById(UpdateEmployeeRequest request, String actionUser);

    EmployeeResponse deleteEmployeeById(DeleteEmployeeRequest request, String actionUser);

}
