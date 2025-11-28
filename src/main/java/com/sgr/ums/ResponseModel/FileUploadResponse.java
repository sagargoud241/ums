package com.sgr.ums.ResponseModel;

public class FileUploadResponse {

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    private String originalFileName;
    private String fileName;
    private String extension;
    private long sizeBytes;
    private String sizeReadable;
    private String mimeType;
    private String savedPath;

    public FileUploadResponse() {
    }

    public FileUploadResponse(String fileName, String extension, long sizeBytes,
                              String sizeReadable, String mimeType, String savedPath,String originalFileName) {
        this.fileName = fileName;
        this.extension = extension;
        this.sizeBytes = sizeBytes;
        this.sizeReadable = sizeReadable;
        this.mimeType = mimeType;
        this.savedPath = savedPath;
        this.originalFileName=originalFileName;
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }
}

