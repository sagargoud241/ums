package com.sgr.ums.RequestModel.UserRequestModel;

import jakarta.validation.constraints.NotBlank;

public class DeleteUserRequest {


    @NotBlank(message = "Id is required")
    private Long id;

    @NotBlank(message = "Remarks is Required")
    private String remarks;


    public Long getId() {
        return id;
    }

    public String getRemarks() {
        return remarks;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    DeleteUserRequest() {
    }

    DeleteUserRequest(Long id, String remarks) {
        this.id = id;
        this.remarks = remarks;
    }


}


