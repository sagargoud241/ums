package com.sgr.ums.Repository;

import com.sgr.ums.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository <Document,Long>{
    Optional<Document> findByUuid(UUID uuid);
    Optional<Document> findByFileName(String fileName);
    Optional<Document> findByAssociationToAndAssociationId(String associationTo, String associationId);

}


