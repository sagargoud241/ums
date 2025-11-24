package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;



public class DeleteEnrollmentRequest {

    @NotBlank(message = "Id is Required")
    private Long id;

    @NotBlank(message = "Remarks is Required")
    private String remarks;
    //getter

    public @NotBlank(message = "Id is Required") Long getId() {
        return id;
    }

    public @NotBlank(message = "Remarks is Required") String getRemarks() {
        return remarks;
    }
    //setter

    public void setId(@NotBlank(message = "Id is Required") Long id) {
        this.id = id;
    }

    public void setRemarks(@NotBlank(message = "Remarks is Required") String remarks) {
        this.remarks = remarks;
    }
    public DeleteEnrollmentRequest(){}

    public DeleteEnrollmentRequest(Long id, String remarks) {
        this.id = id;
        this.remarks = remarks;
    }

    @Override
    public String toString(){
        return "DeleteEnrollmentRequest{"+
                ",id='"+id+'\''+
                ",remarks='"+remarks+'\''+
                '}';
    }
}
