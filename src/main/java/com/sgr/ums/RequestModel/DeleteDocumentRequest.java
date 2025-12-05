package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeleteDocumentRequest {

    @NotNull(message = "Id is Required")
    private Long id;

    @NotBlank(message = "Remarks is Required")
    private String remarks;



    public @NotNull(message = "Id is Required") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Id is Required") Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Remarks is Required") String getRemarks() {
        return remarks;
    }

    public void setRemarks(@NotBlank(message = "Remarks is Required") String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DeleteDocumentRequest(" +
                ",id='" + id + '\'' +
                ",remark='" + remarks + '\'' +
                '}';
    }

}
