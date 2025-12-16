package com.sgr.ums.Employees.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeRequest {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("department")
    private String department;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("remarks")
    private String remarks;

}
