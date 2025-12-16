package com.sgr.ums.Employees.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.util.List;

@Data
public class EmployeeResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String message;

    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("employee")
    EmployeeDTO employee;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("employees")
    List<EmployeeDTO> employees;


}