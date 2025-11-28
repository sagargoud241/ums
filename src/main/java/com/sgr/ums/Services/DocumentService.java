package com.sgr.ums.Services;
import com.sgr.ums.Entity.Document;
import com.sgr.ums.RequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.UpdateDocumentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import org.jspecify.annotations.Nullable;


public interface DocumentService {
    ApiResponse<Document> addDocument(FileUploadResponse fileUploadresponse, AddDocumentRequest request);

    ApiResponse<Document> getDocumentById(Long id);

    @Nullable ApiResponse<Document> getDocumentByUuid(java.util.UUID uuid);

    ApiResponse<Document> getDocumentByFileName(String fileName);

    ApiResponse<Document> getByAssociation(String associationTo, String associationId);

    ApiResponse<Document> inActivateDocument(UpdateDocumentRequest request);
}
