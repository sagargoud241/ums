package com.sgr.ums.RequestModel.AuthorRequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAuthorRequest {
    @NotNull(message = "Id is required")
    private long id;

    @NotBlank(message = "FirstNAme is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "LastName is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;


}
