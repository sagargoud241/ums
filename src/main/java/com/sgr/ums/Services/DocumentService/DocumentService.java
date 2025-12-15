package com.sgr.ums.Services.DocumentService;
import com.sgr.ums.Entity.Document;
import com.sgr.ums.RequestModel.DocumentRequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.ListDocumentRequest;
import com.sgr.ums.RequestModel.DocumentRequestModel.UpdateDocumentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;


public interface DocumentService {
    ApiResponse<Document> addDocument(FileUploadResponse fileUploadresponse, AddDocumentRequest request);

    ApiResponse<Document> getDocumentById(Long id);

    @Nullable ApiResponse<Document> getDocumentByUuid(java.util.UUID uuid);

    ApiResponse<Document> getDocumentByFileName(String fileName);

    ApiResponse<Document> getByAssociation(String associationTo, String associationId);

    ApiResponse<Document> inActivateDocument(UpdateDocumentRequest request);

    ApiResponse<List<Document>> addDocsList(List<ListDocumentRequest> fileList);

    ApiResponse<List<Document>> updateDocsList(List<ListDocumentRequest> fileList);
}
