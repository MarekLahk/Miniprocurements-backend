package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.models.mapper.DocumentMapper;
import ee.taltech.procurementSystemBackend.models.model.Document;
import ee.taltech.procurementSystemBackend.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService extends ServiceBase<Document, DocumentDto>{

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        super(repository, DocumentMapper.INSTANCE);
        this.repository = repository;
    }

    public Document addDocument(Document document) {
        document.setId(null);
        document.setLinkId(null);
//        document.setCreatedAt(null);
//        document.setUpdatedAt(null);
        document = repository.save(document);
        return repository.findById(document.getId()).orElse(null);
    }

    public Optional<Document> getByUUID(UUID documentUUID) {
        return repository.findByLinkId(documentUUID);
    }

}
