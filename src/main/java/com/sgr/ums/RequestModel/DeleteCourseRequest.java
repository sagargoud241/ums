package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeleteCourseRequest {


    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Remarks is Required")
    private String remarks;

    //getter

    public @NotNull(message = "Id is required") Long getId() {
        return id;
    }

    public @NotBlank(message = "Remarks is Required") String getRemarks() {
        return remarks;
    }

    //setter

    public void setId(@NotNull(message = "Id is required") Long id) {
        this.id = id;
    }

    public void setRemarks(@NotBlank(message = "Remarks is Required") String remarks) {
        this.remarks = remarks;
    }

    public DeleteCourseRequest(){}

    public DeleteCourseRequest(Long id, String remarks) {
        this.id = id;
        this.remarks = remarks;
    }
    @Override
    public String toString(){
        return "DeleteCourseRequest{"+
                ",id='"+id+'\''+
                ",remarks='"+remarks+'\''+
                '}';
    }
}

