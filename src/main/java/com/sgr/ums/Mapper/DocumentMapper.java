package com.sgr.ums.Mapper;
import com.sgr.ums.Entity.Document;
import com.sgr.ums.RequestModel.AddDocumentRequest;
import com.sgr.ums.RequestModel.UpdateDocumentRequest;

public class DocumentMapper {
    public static Document addDocument(AddDocumentRequest request) {
        Document document = new Document();
        document.setFileName(request.getFileName());
        document.setFileType(request.getFileType());
        document.setAssociationTo(request.getAssociationTo());
        document.setAssociationId(request.getAssociationId());
        return document;
    }
    public static Document updateDocument( Document document , UpdateDocumentRequest request) {
        document.setFileType(request.getFileType());
        document.setAssociationTo(request.getAssociationTo());
        document.setAssociationId(request.getAssociationId());
        return document;
    }
}