package com.sgr.ums.Services;

import com.sgr.ums.ResponseModel.FileUploadResponse;
import com.sgr.ums.Utilities.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    // Base directory path where files will be stored, injected from application.properties
    @Value("${filePath}")
    private String basePath;

    @Override
    public String uploadFile(MultipartFile file) {

        // Get original filename with extension
        String fileName = file.getOriginalFilename();

        // Validate filename
        if (fileName == null || fileName.isEmpty()) {
            log.error("INVALID_NAME");
            return null;
        }
        fileName = UUID.randomUUID().toString() + fileName;
        // Check if file already exists
        File dir = new File(basePath + fileName);
        if (dir.exists()) {
            log.error("EXIST");
            return null;
        }
        // Create file path object
        Path path = Path.of(basePath + fileName);

        try {
            // Copy uploaded file to destination directory
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UrlResource downloadFile(String fileName) {

        // File reference using provided filename
        File dir = new File(basePath + fileName);

        try {
            // If file exists, return it as a UrlResource for downloading
            if (dir.exists()) {
                log.error("Exists");
                return new UrlResource(dir.toURI());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<String> fileList() {

        // Get directory representing the base path
        File dir = new File(basePath);


        // List all files in the directory
        File[] files = dir.listFiles();

        // Convert file array to a list of filenames
        log.error("INVALID_FileName ");
        return files != null ? Arrays.stream(files).map(File::getName).collect(Collectors.toList()) : null;
    }

    @Override
    public String saveBase64File(String base64, String fileName) throws IOException {

        // Decode Base64 string to byte array
        byte[] fileBytes = Base64.getDecoder().decode(base64);

        // Ensure upload directory exists
        String uploadDir = basePath;
        java.io.File directory = new java.io.File(uploadDir);
        if (!directory.exists()) {
            log.error("Exists");
            directory.mkdirs();
        }

        // Full file path where data will be written
        String filePath = uploadDir + fileName;

        // Write decoded bytes to file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileBytes);
        }
         log.error("INVALID FilePath");
        return filePath;
    }

    @Override
    public FileUploadResponse uploadNewFile(MultipartFile file) {

        // Get original filename
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.trim().isEmpty()) {
            System.out.println("INVALID_NAME");
            log.error("INVALID_NAME");
            return null;
        }

        // Extract file extension
        String extension = "";
        int dotIndex = originalName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = originalName.substring(dotIndex + 1);
        }

        // Create unique filename
        String newFileName = UUID.randomUUID().toString() + "_" + originalName;

        // Full path
        String fullPath = basePath + newFileName;
        File dest = new File(fullPath);

        // Check if exists
        if (dest.exists()) {
            System.out.println("FILE_ALREADY_EXISTS");
            log.error("exists");
            return null;
        }

        try {
            // Save file to disk
            Files.copy(file.getInputStream(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            long sizeBytes = file.getSize();
            String sizeReadable = readableFileSize(sizeBytes);

            // Return response object
            return new FileUploadResponse(
                    newFileName,
                    extension,
                    sizeBytes,
                    sizeReadable,
                    file.getContentType(),
                    fullPath,
                    originalName
            );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FILE_UPLOAD_ERROR: " + e.getMessage());
            log.error("File Upload Error {}",e.getMessage());
            return null;
        }
    }

    private String readableFileSize(long size) {
        if (size <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return String.format("%.2f %s", size / Math.pow(1024, digitGroups), units[digitGroups]);
    }


    @Override
    public FileUploadResponse uploadBase64File(String base64, String originalName) throws IOException {

        if (originalName == null || originalName.trim().isEmpty()) {
            System.out.println("INVALID_NAME");
            log.error("INVALID_NAME");
            return null;
        }

        // Extract file extension
        String extension = "";
        int dotIndex = originalName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = originalName.substring(dotIndex + 1);
        }

        // Create a unique filename
        String newFileName = UUID.randomUUID().toString() + "_" + originalName;

        // Decode Base64 to bytes
        byte[] fileBytes = Base64.getDecoder().decode(base64);

        // Ensure directory exists
        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Full file path
        String fullPath = basePath + newFileName;

        // Write bytes to file
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            fos.write(fileBytes);
        }

        // Get file size
        long sizeBytes = fileBytes.length;
        String sizeReadable = readableFileSize(sizeBytes);

        // Return FileUploadResponse (same structure as Multipart upload)
        return new FileUploadResponse(
                newFileName,        // new unique filename
                extension,          // file extension
                sizeBytes,          // size in bytes
                sizeReadable,       // size in readable format
                "application/octet-stream", // no mime from base64 → default
                fullPath,           // saved file full path
                originalName        // original client file name
        );
    }

    @Override
    public FileUploadResponse uploadBase64File(String base64) throws IOException {

        if (base64 == null || base64.trim().isEmpty()) {
            System.out.println("EMPTY_BASE64_DATA");
            log.error("EMPTY_BASE64_DATA");
            return null;
        }

        // Decode Base64 to bytes
        byte[] fileBytes = Base64.getDecoder().decode(base64);

        // default extension when no original name is given
        String extension = "bin";

        // Create unique filename
        String newFileName = UUID.randomUUID().toString() + "." + extension;

        // Ensure directory exists
        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Full file path
        String fullPath = basePath + newFileName;

        // Save file
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            fos.write(fileBytes);
        }

        long sizeBytes = fileBytes.length;
        String sizeReadable = readableFileSize(sizeBytes);

        // Build and return response
        return new FileUploadResponse(
                newFileName,            // generated new file name
                extension,              // extension (default)
                sizeBytes,              // size in bytes
                sizeReadable,           // human readable size
                "application/octet-stream",  // mime type unknown
                fullPath,               // location saved
                newFileName                    // original file name (none)
        );
    }

}


