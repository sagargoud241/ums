package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class DeleteStudentRequest {


    @NotBlank(message = "Id is Required")
    private Long id;

    @NotBlank(message="remarks is Required ")
    private String remarks;


    //getter
    public @NotBlank(message = "Id is Required") Long getId() {return id;}

    public @NotBlank(message = "remarks is Required ") String getRemarks() {return remarks;}



    //setter

    public void setId(@NotBlank(message = "Id is Required") Long id) {this.id = id;}

    public void setRemarks(@NotBlank(message = "remarks is Required ") String remarks) {this.remarks = remarks;}


    public DeleteStudentRequest(){}

    public DeleteStudentRequest(Long id, String remarks) {
        this.id = id;
        this.remarks = remarks;
    }
}
