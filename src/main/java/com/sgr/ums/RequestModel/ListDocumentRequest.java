package com.sgr.ums.RequestModel;

public class ListDocumentRequest {
    private long id;
    private String fileName;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAssociationId() {
        return associationId;
    }

    public void setAssociationId(String associationId) {
        this.associationId = associationId;
    }

    public String getAssociationTo() {
        return associationTo;
    }

    public void setAssociationTo(String associationTo) {
        this.associationTo = associationTo;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public String getSizeReadable() {
        return sizeReadable;
    }

    public void setSizeReadable(String sizeReadable) {
        this.sizeReadable = sizeReadable;
    }

    private String fileType;
    private String associationTo;
    private String associationId;
    private String base64Data;
    private String originalFileName;
    private String extension;
    private long sizeBytes;
    private String sizeReadable;
    private String mimeType;
    private String savedPath;
}
