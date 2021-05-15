package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends RepositoryInterface<Document>{

    Optional<Document> findByLinkId(UUID uuid);
}
