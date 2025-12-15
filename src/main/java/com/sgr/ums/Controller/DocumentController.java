package com.sgr.ums.Controller;

import com.sgr.ums.Entity.Document;
import com.sgr.ums.RequestModel.DocumentRequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.ListDocumentRequest;
import com.sgr.ums.RequestModel.DocumentRequestModel.UpdateDocumentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import com.sgr.ums.Services.DocumentService.DocumentService;
import com.sgr.ums.Services.FileService.FileService;
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
import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/list/base64")
    public ResponseEntity<ApiResponse<List<Document>>> addDocs(@RequestBody List<AddDocumentRequest> requests) throws IOException {

        List<ListDocumentRequest> fileList = new ArrayList<>();

        for (AddDocumentRequest req : requests) {

            if (req.getBase64Data() == null || req.getFileName() == null) {
                log.error("INVALID BASE64 OR FILENAME: {}", JsonUtils.toJson(req));
                return ResponseEntity.ok(ApiResponse.failure("Missing base64Data or fileName"));
            }

            log.info("Uploading Base64 file: {}", req.getFileName());

            FileUploadResponse fileUploadresponse =
                    fileService.uploadBase64File(req.getBase64Data(), req.getFileName());

            if (fileUploadresponse == null) {
                log.error("Base64 upload failed for file: {}", req.getFileName());
                return ResponseEntity.ok(ApiResponse.failure("Base64 upload failed"));
            }
            ListDocumentRequest file=new ListDocumentRequest();

            file.setFileName(fileUploadresponse.getFileName());
            file.setExtension(fileUploadresponse.getExtension());
            file.setSizeBytes(fileUploadresponse.getSizeBytes());
            file.setSizeReadable(fileUploadresponse.getSizeReadable());
            file.setMimeType(fileUploadresponse.getMimeType());
            file.setOriginalFileName(fileUploadresponse.getOriginalFileName());
            file.setSavedPath(fileUploadresponse.getSavedPath());

            file.setFileName(req.getFileName());
            file.setFileType(req.getFileType());
            file.setAssociationTo(req.getAssociationTo());
            file.setAssociationId(req.getAssociationId());
            fileList.add(file);

        }

        if (fileList != null) {
            log.info("Adding Base64 is starting{}",JsonUtils.toJson(fileList));

            return ResponseEntity.ok(documentService.addDocsList(fileList));
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

    @PutMapping("/base64/list")
    public ResponseEntity<ApiResponse<List<Document>>> updateBase64Documents(@RequestBody List<UpdateDocumentRequest> requestList) throws IOException {

        log.info("Multiple Base64 update request: {}", JsonUtils.toJson(requestList));
        List<ListDocumentRequest> updatedDocuments = new ArrayList<>();

        //List<Document> updatedDocuments = new ArrayList<>();

        for (UpdateDocumentRequest request : requestList) {
            try {
                // 1. Upload Base64 File
                FileUploadResponse fileResponse = fileService.uploadBase64File(
                        request.getBase64Data(),
                        request.getFileName()
                );

                if (fileResponse == null) {
                    log.error("Base64 upload failed for request: {}", request);
                    continue;
                }
                if (fileResponse == null) {
                    log.error("Base64 upload failed for file: {}", request.getFileName());
                    return ResponseEntity.ok(ApiResponse.failure("Base64 upload failed"));
                }
                ListDocumentRequest file=new ListDocumentRequest();

                file.setFileName(fileResponse.getFileName());
                file.setExtension(fileResponse.getExtension());
                file.setSizeBytes(fileResponse.getSizeBytes());
                file.setSizeReadable(fileResponse.getSizeReadable());
                file.setMimeType(fileResponse.getMimeType());
                file.setOriginalFileName(fileResponse.getOriginalFileName());
                file.setSavedPath(fileResponse.getSavedPath());

                file.setId(request.getId());
                file.setFileName(request.getFileName());
                file.setFileType(request.getFileType());
                file.setAssociationTo(request.getAssociationTo());
                file.setAssociationId(request.getAssociationId());
                updatedDocuments.add(file);

//                // 2. Inactivate old document
//                ApiResponse<Document> inactivateResponse = documentService.inActivateDocument(request);
//
//                if (inactivateResponse == null || !"success".equals(inactivateResponse.getCode())) {
//                    log.error("Failed to inactivate document for request: {}", request);
//                    continue;
//                }
//
//                // 3. Prepare AddDocumentRequest for new upload entry
//                AddDocumentRequest addRequest = new AddDocumentRequest();
//                addRequest.setAssociationId(request.getAssociationId());
//                addRequest.setAssociationTo(request.getAssociationTo());
//                addRequest.setFileType(request.getFileType());
//
//                // 4. Add new document entry
//                ApiResponse<Document> addResponse = documentService.addDocument(fileResponse, addRequest);
//
//                if (addResponse != null && "success".equals(addResponse.getCode())) {
//                    updatedDocuments.add(addResponse.getData());
//                }

            } catch (Exception e) {
                log.error("Error processing update request: {}", request, e);
            }
        }

        if (updatedDocuments != null) {
            log.info("updated Documents Base64 is starting{}",JsonUtils.toJson(updatedDocuments));
            return ResponseEntity.ok(documentService.updateDocsList(updatedDocuments));
        } else {
            log.error("Please Upload Base64 Document");
            return ResponseEntity.ok(ApiResponse.failure("Please upload document"));
        }

       // return ResponseEntity.ok(ApiResponse.success(updatedDocuments, "Documents updated successfully"));
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
