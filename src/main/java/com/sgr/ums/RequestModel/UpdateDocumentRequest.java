package com.sgr.ums.RequestModel;

import jakarta.validation.constraints.NotBlank;

public class UpdateDocumentRequest {

    @NotBlank(message = "ID is Required")
    private long id;

    @NotBlank(message = "FileType is Required")
    private String fileType;

    @NotBlank(message = "AssociationTo is Required")
    private String associationTo;

    @NotBlank(message = "AssociationId is Required")
    private String associationId;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    private String base64Data;

    public @NotBlank(message = "FileType is Required") String getFileType() {
        return fileType;
    }

    public void setFileType(@NotBlank(message = "FileType is Required") String fileType) {
        this.fileType = fileType;
    }

    public @NotBlank(message = "AssociationId is Required") String getAssociationId() {
        return associationId;
    }

    public void setAssociationId(@NotBlank(message = "AssociationId is Required") String associationId) {
        this.associationId = associationId;
    }

    public @NotBlank(message = "AssociationTo is Required") String getAssociationTo() {
        return associationTo;
    }

    public void setAssociationTo(@NotBlank(message = "AssociationTo is Required") String associationTo) {
        this.associationTo = associationTo;
    }

    public @NotBlank(message = "ID is Required") long getId() {
        return id;
    }

    public void setId(@NotBlank(message = "ID is Required") long id) {
        this.id = id;
    }


    public UpdateDocumentRequest() {
    }

    public UpdateDocumentRequest(long id, String fileType, String associationTo, String associationId) {
        this.id = id;
        this.fileType = fileType;
        this.associationTo = associationTo;
        this.associationId = associationId;
    }


    @Override
    public String toString() {
        return "UpdateDocumentRequest=(" +
                ",id='" + id + '\'' +
                ",fileType='" + fileType + '\'' +
                ",associationTo='" + associationTo + '\'' +
                ",associationId='" + associationId + '\'' +
                '}';
    }
}
