package com.sgr.ums.Employees.Repository;

import com.sgr.ums.Employees.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
