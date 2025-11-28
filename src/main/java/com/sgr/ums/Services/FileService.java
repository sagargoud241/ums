package com.sgr.ums.Services;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface FileService {
    String uploadFile(MultipartFile file);

    UrlResource downloadFile(String fileName);

    List<String> fileList();

    String saveBase64File(String base64, String fileName) throws IOException;

    FileUploadResponse uploadNewFile(MultipartFile file);

    FileUploadResponse uploadBase64File(String base64, String originalName) throws IOException;

    FileUploadResponse uploadBase64File(String base64) throws IOException;
}
