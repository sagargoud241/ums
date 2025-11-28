package com.sgr.ums.Services;

import com.sgr.ums.Entity.Document;
import com.sgr.ums.Mapper.DocumentMapper;
import com.sgr.ums.Repository.DocumentRepository;
import com.sgr.ums.RequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.UpdateDocumentRequest;
import com.sgr.ums.ResponseModel.ApiResponse;
import com.sgr.ums.ResponseModel.FileUploadResponse;
import com.sgr.ums.Utilities.JsonUtils;
import com.sgr.ums.Utilities.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {
    private static final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public ApiResponse<Document> addDocument(FileUploadResponse fileUploadresponse, AddDocumentRequest request) {
        try {
            log.info("Add doc request is  {}", JsonUtils.toJson(request));
            Document document = DocumentMapper.addDocument(request);
            document.setCreatedBy(Utility.getDefaultUsername());
            document.setCreatedDate((LocalDateTime.now()));
            document.setFileName(fileUploadresponse.getFileName());
            document.setExtension(fileUploadresponse.getExtension());
            document.setSizeBytes(fileUploadresponse.getSizeBytes());
            document.setSizeReadable(fileUploadresponse.getSizeReadable());
            document.setMimeType(fileUploadresponse.getMimeType());
            document.setOriginalFileName(fileUploadresponse.getOriginalFileName());
            document.setFilePath(fileUploadresponse.getSavedPath());
            document.setActive(true);
            document.setDeleted(false);
            documentRepository.save(document);
            return ApiResponse.success(document, "Add SuccessFully");
        } catch (Exception e) {
            log.error("Exception while add document", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Document> getDocumentById(Long id) {
        try {
            log.info("Get document request by id is{}", JsonUtils.toJson(id));
            Optional<Document> optional = documentRepository.findById(id);
            if (optional.isEmpty()) {
                log.error("Document Id Not found ");
                return ApiResponse.failure("Document not Found");
            }
            if (optional.get().getDeleted()) {
                log.error("Document is already delete");
                return ApiResponse.failure("Document not Found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception while get document by Long id", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Document> getDocumentByUuid(UUID uuid) {
        try {
            log.info("get document request by uuid is {}", JsonUtils.toJson(uuid));
            Optional<Document> optional = documentRepository.findByUuid(uuid);
            if (optional.isEmpty()) {
                log.error("Document uuid Not found ");
                return ApiResponse.failure("Document not Found");
            }
            if (optional.get().getDeleted()) {
                log.error("Document is already delete");
                return ApiResponse.failure("Document not Found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception while get document by uuid", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }

    @Override
    public ApiResponse<Document> getDocumentByFileName(String fileName) {
        try {
            log.info("get document request by file name is {}", JsonUtils.toJson(fileName));
            Optional<Document> optional = documentRepository.findByFileName(fileName);
            if (optional.isEmpty()) {
                log.error("Document fileName  Not found ");
                return ApiResponse.failure("Document not Found");
            }
            if (optional.get().getDeleted()) {
                log.error("Document is already delete");
                return ApiResponse.failure("Document not Found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception while get document by fileName", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }


    @Override
    public ApiResponse<Document> getByAssociation(String associationTo, String associationId) {
        try {
            log.info("Get document by Association is{}", JsonUtils.toJson(associationId));
            Optional<Document> optional = documentRepository.findByAssociationToAndAssociationId(associationTo, associationId);
            if (optional.isEmpty()) {
                log.error("Document Association  Not found ");
                return ApiResponse.failure("Document not Found");
            }
            if (optional.get().getDeleted()) {
                log.error("Document is already delete");
                return ApiResponse.failure("Document not Found");
            }
            return ApiResponse.success(optional.get(), "Fetch SuccessFully");
        } catch (Exception e) {
            log.error("Exception while get document by Association", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }


    @Override
    public ApiResponse<Document> inActivateDocument(UpdateDocumentRequest request) {
        try {
            log.info("Get document by InActive is{}", JsonUtils.toJson(request));
            Optional<Document> optional = documentRepository.findById(request.getId());
            if (optional.isEmpty()) {
                log.error("Document InActive  Not found ");
                return ApiResponse.failure("Document not Found");
            }
            if (optional.get().getDeleted()) {
                log.error("Document is already delete ");
                return ApiResponse.failure("Document not Found");
            }
            if (!optional.get().getActive()) {
                log.error("Document Active  Not found ");
                return ApiResponse.failure("Document not Found");
            }
            Document document = optional.get();
            document.setUpdatedBy(Utility.getDefaultUsername());
            document.setUpdatedDate(LocalDateTime.now());
            document.setActive(false);
            documentRepository.save(document);
            return ApiResponse.success(document, "UpdateSuccessFully");
        } catch (Exception e) {
            log.error("Exception while inActive the Document", e.getMessage());
            return ApiResponse.exception(e.getMessage());
        }
    }
}



