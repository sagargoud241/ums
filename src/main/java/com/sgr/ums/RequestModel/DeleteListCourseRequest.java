package com.sgr.ums.RequestModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class DeleteListCourseRequest {

    @NotEmpty(message = "IDs list cannot be empty")
    private List<Long> ids;

    @NotNull(message = "Remarks cannot be null")
    private String remarks;

    // Constructors
    public DeleteListCourseRequest() {}

    public DeleteListCourseRequest(List<Long> ids, String remarks) {
        this.ids = ids;
        this.remarks = remarks;
    }

    // Getters and Setters
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DeleteListCourseRequest{" +
                "ids=" + ids +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

