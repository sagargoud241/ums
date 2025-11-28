package com.sgr.ums.RequestModel;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AddDocumentRequest {

    @NotBlank(message = "FileName is required ")
    private String fileName;

    @NotBlank(message = "FileType is required")
    private String fileType;

    private String associationTo;

    private String base64Data;




    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public String getAssociationTo() {
        return associationTo;
    }

    public void setAssociationTo(String associationTo) {
        this.associationTo = associationTo;
    }

    public String getAssociationId() {
        return associationId;
    }

    public void setAssociationId(String associationId) {
        this.associationId = associationId;
    }

    private String associationId;

    public @NotBlank(message = "FileName is required ") String getFileName() {
        return fileName;
    }

    public void setFileName(@NotBlank(message = "FileName is required ") String fileName) {
        this.fileName = fileName;
    }

    public @NotBlank(message = "FileType is required") String getFileType() {
        return fileType;
    }

    public void setFileType(@NotBlank(message = "FileType is required") String fileType) {
        this.fileType = fileType;
    }

    public AddDocumentRequest() {
    }

    public AddDocumentRequest(String fileName, String fileType, String associationTo, String associationId) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.associationTo = associationTo;
        this.associationId = associationId;
    }


    @Override
    public String toString() {
        return " AddDocumentRequest(" +
                ",fileName='" + fileName + '\'' +
                ",fileType='" + fileType + '\'' +
                ",associationTo='" + associationTo + '\'' +
                ",associationId='" + associationId + '\'' +
                '}';
    }
}
