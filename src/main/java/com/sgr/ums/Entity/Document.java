package com.sgr.ums.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="document")
public class Document extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private long id;

    @Column(name="extension")
    private String extension;

    @Column(name="size_bytes")
    private long sizeBytes;

    @Column(name="size_readable")
    private String sizeReadable;

    @Column(name="mime_type")
    private String mimeType;

    @Column(name="original_file_name")
    private String originalFileName;

    @Column(name="uuid",unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name="file_Name",nullable = false)
    private String fileName;

    @Column(name="file_Type",nullable = false)
    private String fileType;

    @Column(name="association_to")
    private String associationTo;

    @Column(name="association_id")
    private String associationId;


    @Column(name="description")
    private String description;

    @Column(name="file_Data")
    private String fileData;

    @Column(name="file_Path",nullable = false)
    private String filePath;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }



    public String getSizeReadable() {
        return sizeReadable;
    }

    public void setSizeReadable(String sizeReadable) {
        this.sizeReadable = sizeReadable;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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



    //getter and setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Document(){}


}
