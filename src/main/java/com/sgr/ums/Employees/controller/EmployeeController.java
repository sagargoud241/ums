package com.sgr.ums.Employees.controller;

import com.sgr.ums.Employees.Model.DeleteEmployeeRequest;
import com.sgr.ums.Employees.Model.EmployeeRequest;
import com.sgr.ums.Employees.Model.EmployeeResponse;
import com.sgr.ums.Employees.Model.UpdateEmployeeRequest;
import com.sgr.ums.Employees.Service.EmployeeService;
import com.sgr.ums.Employees.config.EmployeesResponseMessages;
import com.sgr.ums.Utilities.Utility;
import com.sgr.ums.apiservices.configs.ApiResponseCodes;
import com.sgr.ums.apiservices.configs.ApiResponseMessages;
import com.sgr.ums.apiservices.models.GenericApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    ResponseEntity<GenericApiResponse> addEmployee(@Valid @RequestBody EmployeeRequest req) {
        log.info("Received request to add employee");
        GenericApiResponse apiresponse = new GenericApiResponse();
        apiresponse.setStatus(ApiResponseCodes.FAILURE);
        apiresponse.setMessageCode(ApiResponseMessages.CREATION_FAILED.name());

        EmployeeResponse response = new EmployeeResponse();
        try {
            if (req != null) {
                response = employeeService.add(req, Utility.getDefaultUsername());

                if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {
                    apiresponse.setEmployeeResponse(response);
                    apiresponse.setStatus(ApiResponseCodes.SUCCESS);
                    apiresponse.setMessageCode(ApiResponseMessages.CREATED_SUCCESSFULLY.name());

                } else {
                    apiresponse.setStatus(ApiResponseCodes.FAILURE);
                    apiresponse.setMessageCode(ApiResponseMessages.CREATION_FAILED.name());
                }
            } else {
                apiresponse.setStatus(ApiResponseCodes.FAILURE);
                apiresponse.setMessageCode(ApiResponseMessages.CREATION_FAILED.name());
            }

        } catch (Exception e) {
            log.error("Exception occurred while adding employee:{}", e.getMessage(), e);
            apiresponse.setStatus(ApiResponseCodes.FAILURE);
            apiresponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }
        log.info("Returning response for add employee API: {}", apiresponse);
        return ResponseEntity.ok(apiresponse);
    }

    @GetMapping
    ResponseEntity<GenericApiResponse> listEmployee() {
        log.info("Received  the list of Employee");
        GenericApiResponse apiresponse = new GenericApiResponse();
        apiresponse.setStatus(ApiResponseCodes.FAILURE);
        apiresponse.setMessageCode(ApiResponseMessages.CREATION_FAILED.name());

        EmployeeResponse response = new EmployeeResponse();
        try {
            response = employeeService.list(Utility.getDefaultUsername());
            if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {
                apiresponse.setEmployeeResponse(response);
                apiresponse.setStatus(ApiResponseCodes.SUCCESS);
                apiresponse.setMessageCode(ApiResponseMessages.DATA_RETRIEVED_SUCCESSFULLY.name());

            } else {
                apiresponse.setStatus(ApiResponseCodes.FAILURE);
                apiresponse.setMessageCode(ApiResponseMessages.ENTITY_NOT_FOUND.name());
            }

        } catch (Exception e) {
            log.error("Exception occurred while List the employee:{}", e.getMessage(), e);
            apiresponse.setStatus(ApiResponseCodes.FAILURE);
            apiresponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }
        log.info("Returning response for List employee API: {}", apiresponse);
        return ResponseEntity.ok(apiresponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericApiResponse> getEmployeeById(@PathVariable Long id) {
        log.info("Received request to getting the Employee BY id");
        GenericApiResponse apiResponse = new GenericApiResponse();
        apiResponse.setStatus(ApiResponseCodes.FAILURE);
        apiResponse.setMessageCode(ApiResponseMessages.ENTITY_NOT_FOUND.name());
        try {
            EmployeeResponse response = employeeService.getEmployeeById(id);
            if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {
                apiResponse.setEmployeeResponse(response);
                apiResponse.setStatus(ApiResponseCodes.SUCCESS);
                apiResponse.setMessageCode(ApiResponseMessages.DATA_RETRIEVED_SUCCESSFULLY.name());
            } else {
                apiResponse.setStatus(ApiResponseCodes.FAILURE);
            }
        } catch (Exception e) {
            log.error("Exception occurred while Getting EmployeeById:{}", e.getMessage(), e);
            apiResponse.setStatus(ApiResponseCodes.FAILURE);
            apiResponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }
        log.info("Returning response for Getting the employee API: {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping
    public ResponseEntity<GenericApiResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest request) {
        log.info("Received request to updateEmployee");
        GenericApiResponse apiresponse = new GenericApiResponse();
        apiresponse.setStatus(ApiResponseCodes.FAILURE);
        apiresponse.setMessageCode(ApiResponseMessages.UPDATE_FAILED.name());

        EmployeeResponse response = new EmployeeResponse();

        try {
            if (request != null) {

                response = employeeService.UpdateEmployeeById(request, Utility.getDefaultUsername());

                if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {

                    apiresponse.setEmployeeResponse(response);
                    apiresponse.setStatus(ApiResponseCodes.SUCCESS);
                    apiresponse.setMessageCode(ApiResponseMessages.UPDATED_SUCCESSFULLY.name());

                } else {
                    apiresponse.setStatus(ApiResponseCodes.FAILURE);
                    apiresponse.setMessageCode(ApiResponseMessages.UPDATE_FAILED.name());
                }

            } else {
                apiresponse.setStatus(ApiResponseCodes.FAILURE);
                apiresponse.setMessageCode(ApiResponseMessages.UPDATE_FAILED.name());
            }

        } catch (Exception e) {
            log.error("Exception occurred while Updating the Employee:{}", e.getMessage(), e);
            apiresponse.setStatus(ApiResponseCodes.FAILURE);
            apiresponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }
        log.info("Returning response for Updating the Employee API: {}", apiresponse);
        return ResponseEntity.ok(apiresponse);
    }

    @DeleteMapping
    public ResponseEntity<GenericApiResponse> deleteEmployee(@Valid @RequestBody DeleteEmployeeRequest request) {
        log.info("Received request to deleting the Employee");
        GenericApiResponse apiresponse = new GenericApiResponse();
        apiresponse.setStatus(ApiResponseCodes.FAILURE);
        apiresponse.setMessageCode(ApiResponseMessages.DELETION_FAILED.name());
        EmployeeResponse response = new EmployeeResponse();

        try {
            if (request != null) {

                response = employeeService.deleteEmployeeById(request, Utility.getDefaultUsername());

                if (response != null && response.getMessage().equals(EmployeesResponseMessages.SUCCESS.toString())) {

                    apiresponse.setEmployeeResponse(response);
                    apiresponse.setStatus(ApiResponseCodes.SUCCESS);
                    apiresponse.setMessageCode(ApiResponseMessages.DELETED_SUCCESSFULLY.name());

                } else {
                    apiresponse.setStatus(ApiResponseCodes.FAILURE);
                    apiresponse.setMessageCode(ApiResponseMessages.DELETION_FAILED.name());
                }

            } else {
                apiresponse.setStatus(ApiResponseCodes.FAILURE);
                apiresponse.setMessageCode(ApiResponseMessages.DELETION_FAILED.name());
            }

        } catch (Exception e) {
            log.error("Exception occurred while Deleting the Employee:{}", e.getMessage(), e);
            apiresponse.setStatus(ApiResponseCodes.FAILURE);
            apiresponse.setMessageCode(ApiResponseMessages.REQUEST_TERMINATED.name());
        }
        log.info("Returning response for Deleting the Employee API: {}", apiresponse);
        return ResponseEntity.ok(apiresponse);
    }

}