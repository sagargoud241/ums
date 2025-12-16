package com.sgr.ums.Employees.Service;

import com.sgr.ums.Employees.Mapper.EmployeeDtoMapper;
import com.sgr.ums.Employees.Mapper.EmployeeMapper;
import com.sgr.ums.Employees.Model.*;
import com.sgr.ums.Employees.Repository.EmployeeRepository;
import com.sgr.ums.Employees.config.EmployeesResponseMessages;
import com.sgr.ums.Employees.config.EmployeesStatusCode;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepository repository;

    @Override
    public EmployeeResponse add(EmployeeRequest request, String actionUser) {
        log.info("Adding new Employee:{}", JsonUtils.toJson(request));
        EmployeeResponse response = new EmployeeResponse();
        response.setMessage(EmployeesResponseMessages.FAILURE.toString());
        try {
            // create mapper class
            Employee entity = new Employee();
            entity = EmployeeMapper.addEmployee(request);
            entity.setUuid(UUID.randomUUID());
            entity.setCreatedBy(actionUser);
            entity.setIsActive(true);
            entity.setStatus(EmployeesStatusCode.ACTIVE);
            entity.setCreatedDate(Utility.getCurrentDateTime());
            entity.setCreatedDateUtc(Utility.getCurrentDateTime());
            entity = repository.save(entity);
            response.setEmployee(EmployeeDtoMapper.toDto(entity));
            response.setId(entity.getId());
            response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
        } catch (Exception e) {
            log.error("Exception while adding the Employee:{}", e.getMessage(), e);
            response.setMessage(EmployeesResponseMessages.REQUEST_TERMINATED.toString());
        }
        return response;

    }

    @Override
    public EmployeeResponse list(String actionUser) {
        log.info("List Employee:{}", JsonUtils.toJson(actionUser));
        EmployeeResponse response = new EmployeeResponse();
        response.setMessage(EmployeesResponseMessages.FAILURE.toString());
        try {
            List<Employee> employees = repository.findAll();
            if (employees.isEmpty()) {
                response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
            } else if (employees.size() < 1) {
                response.setMessage(EmployeesResponseMessages.NOT_AVAILABLE.toString());
            } else {
                response.setEmployees(EmployeeDtoMapper.toDtoList(employees));
                response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
            }
        } catch (Exception e) {
            log.error("Exception while Listing the Employee:{}", e.getMessage(), e);
            response.setMessage(EmployeesResponseMessages.REQUEST_TERMINATED.toString());
        }
        return response;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        log.info("Getting theEmployee By id:{}", JsonUtils.toJson(id));
        EmployeeResponse response = new EmployeeResponse();
        try {
            Optional<Employee> optional = repository.findById(id);
            if (optional.isEmpty()) {
                response.setMessage(EmployeesResponseMessages.NOT_AVAILABLE.toString());
                return response;
            }
            response.setEmployee(EmployeeDtoMapper.toDto(optional.get()));
            response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
        } catch (Exception e) {
            log.error("Exception while getting  the employee By id:{}", e.getMessage(), e);
            response.setMessage(EmployeesResponseMessages.REQUEST_TERMINATED.toString());
        }
        return response;

    }

    @Override
    public EmployeeResponse UpdateEmployeeById(UpdateEmployeeRequest request, String actionUser) {
        log.info("Updating  the Employee By id:{}", JsonUtils.toJson(request));
        Optional<Employee> optional = repository.findById(request.getId());
        EmployeeResponse response = new EmployeeResponse();
        try {
            if (optional.isEmpty()) {
                response.setMessage(EmployeesResponseMessages.FAILURE.toString());
            }
            if (optional.get().getIsActive() == false) {
                response.setMessage(EmployeesResponseMessages.FAILURE.toString());
            }
            Employee emp = optional.get();
            emp = EmployeeMapper.UpdateEmployee(emp, request);
            emp.setUpdatedBy(actionUser);
            emp.setUpdatedDate(Utility.getCurrentDateTime());
            emp.setUpdatedDateUtc(Utility.getCurrentDateTime());
            emp = repository.save(emp);
            response.setEmployee(EmployeeDtoMapper.toDto(emp));
            response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
        } catch (Exception e) {
            log.error("Exception while Updating the Employee:{}", e.getMessage(), e);
            response.setMessage(EmployeesResponseMessages.REQUEST_TERMINATED.toString());
        }
        return response;
    }

    @Override
    public EmployeeResponse deleteEmployeeById(DeleteEmployeeRequest request, String actionUser) {
        log.info("Deleting the Employee  the Employee By id:{}", JsonUtils.toJson(request));
        Optional<Employee> optional = repository.findById(request.getId());
        EmployeeResponse response = new EmployeeResponse();
        try {
            if (optional.isEmpty()) {
                response.setMessage(EmployeesResponseMessages.FAILURE.toString());
            }
            if (optional.get().getIsActive() == false) {
                response.setMessage(EmployeesResponseMessages.FAILURE.toString());
            }
            Employee emp = optional.get();
            emp.setUpdatedBy(actionUser);
            emp.setIsActive(false);
            emp.setStatus(EmployeesStatusCode.DELETED);
            emp.setUpdatedDate(Utility.getCurrentDateTime());
            emp.setUpdatedDateUtc(Utility.getCurrentDateTime());
            emp = repository.save(emp);
            response.setMessage(EmployeesResponseMessages.SUCCESS.toString());
        } catch (Exception e) {
            log.error("Exception while Deleting the Employee:{}", e.getMessage(), e);
            response.setMessage(EmployeesResponseMessages.REQUEST_TERMINATED.toString());
        }
        return response;
    }

}
