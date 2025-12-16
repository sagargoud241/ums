package com.sgr.ums.Employees.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteEmployeeRequest {

    @NotNull(message = "Id is required")
    private Long id;
    @NotBlank(message = "Remarks is Required")
    private String remarks;
}
