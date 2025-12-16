package com.sgr.ums.apiservices.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sgr.ums.Employees.Model.EmployeeResponse;
import com.sgr.ums.apiservices.configs.ApiResponseCodes;
import com.sgr.ums.users.models.UserResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenericApiResponse {
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ApiResponseCodes status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message_code")
    private String messageCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("employee_response")
    private EmployeeResponse employeeResponse;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_response")
    private UserResponse userResponse;
}
