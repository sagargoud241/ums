package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Document;
import com.sgr.ums.RequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.UpdateDocumentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import com.sgr.ums.Services.DocumentService;
import com.sgr.ums.Services.FileService;
import com.sgr.ums.Utilities.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/document")
public class DocumentController {
    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;
    @Autowired
    private FileService fileService;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<Document>> addDocument(@RequestPart("file") MultipartFile file, @RequestPart("request") String requestJson) {
        AddDocumentRequest request = new ObjectMapper().readValue(requestJson, AddDocumentRequest.class);
        FileUploadResponse fileResponse = new FileUploadResponse();
        fileResponse = fileService.uploadNewFile(file);

        if (fileResponse != null) {
            log.info("Adding Document is starting {}", JsonUtils.toJson(requestJson));
            return ResponseEntity.ok(documentService.addDocument(fileResponse, request));
        } else {
            log.error("Upload document please");
            return ResponseEntity.ok(ApiResponse.failure("Please upload document"));
        }

    }

    @PutMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<Document>> updateDocument(
            @RequestPart("file") MultipartFile file,
            @RequestPart("request") String requestJson) {
        UpdateDocumentRequest request = new ObjectMapper().readValue(requestJson, UpdateDocumentRequest.class);

        FileUploadResponse fileResponse = new FileUploadResponse();
        fileResponse = fileService.uploadNewFile(file);

        if (fileResponse != null) {
            ApiResponse<Document> response = documentService.inActivateDocument(request);
            if (response != null && response.getCode().equals("success")) {
                AddDocumentRequest addRequest = new AddDocumentRequest();
                addRequest.setAssociationId(request.getAssociationId());
                addRequest.setAssociationTo(request.getAssociationTo());
                addRequest.setFileType((request.getFileType()));
                log.info("Updating Document is starting{}",JsonUtils.toJson(requestJson));
                return ResponseEntity.ok(documentService.addDocument(fileResponse, addRequest));
            } else {
                log.error("Invalid Request");
                return ResponseEntity.ok(ApiResponse.failure("Invalid request"));
            }
        } else {
            log.error("Invalid document");
            return ResponseEntity.ok(ApiResponse.failure("Please upload document"));
        }
    }


    @PostMapping("/base64")
    public ResponseEntity<ApiResponse<Document>> addbase64Document(@RequestBody AddDocumentRequest request) throws IOException {

        FileUploadResponse fileResponse = new FileUploadResponse();
        fileResponse = fileService.uploadBase64File(request.getBase64Data(), request.getFileName());
        //  fileResponse = fileService.uploadBase64File(request.getBase64Data());
        if (fileResponse != null) {
            log.info("Adding Base64 is starting{}",JsonUtils.toJson(request));
            return ResponseEntity.ok(documentService.addDocument(fileResponse, request));
        } else {
            log.error("Please Upload Base64 Document");
            return ResponseEntity.ok(ApiResponse.failure("Please upload document"));
        }
    }


    @PutMapping("/base64")
    public ResponseEntity<ApiResponse<Document>> updatebase64Document(@RequestBody UpdateDocumentRequest request) throws IOException {

        FileUploadResponse fileResponse = new FileUploadResponse();
        fileResponse = fileService.uploadBase64File(request.getBase64Data(), request.getFileName());

        if (fileResponse != null) {
            ApiResponse<Document> response = documentService.inActivateDocument(request);
            if (response != null && response.getCode().equals("success")) {
                AddDocumentRequest addRequest = new AddDocumentRequest();
                addRequest.setAssociationId(request.getAssociationId());
                addRequest.setAssociationTo(request.getAssociationTo());
                addRequest.setFileType((request.getFileType()));
                log.info("Updating Base64 is starting{}",JsonUtils.toJson(request));
                return ResponseEntity.ok(documentService.addDocument(fileResponse, addRequest));

            } else {
                log.error("Invalid Base64 Request");
                return ResponseEntity.ok(ApiResponse.failure("Invalid request"));
            }
        } else {
            log.error("Please upload Base64 Document");
            return ResponseEntity.ok(ApiResponse.failure("Please upload document"));
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Document>> getDocumentById(@PathVariable Long id) {
        log.info("Get the Document by id {}",JsonUtils.toJson(id));
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<ApiResponse<Document>> getDocumentByUuid(@PathVariable UUID uuid) {
        log.info("Get the Document by Uuid {}",JsonUtils.toJson(uuid));
        return ResponseEntity.ok(documentService.getDocumentByUuid(uuid));
    }

    @GetMapping("/fileName/{fileName}")
    public ResponseEntity<ApiResponse<Document>> getDocumentByFileName(@PathVariable String fileName) {
        log.info("Get the Document by fileName {}",JsonUtils.toJson(fileName));
        return ResponseEntity.ok(documentService.getDocumentByFileName(fileName));
    }

    @GetMapping("fileName/{associationTo}/{associationId}")
    public ResponseEntity<ApiResponse<Document>> getByAssociation(@PathVariable String associationTo, @PathVariable String associationId) {
        log.info("Get the Document by Association {}",JsonUtils.toJson(associationId));
        return ResponseEntity.ok(documentService.getByAssociation(associationTo, associationId));
    }


}
