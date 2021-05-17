package ee.taltech.procurementSystemBackend.controller;


import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.models.model.Document;
import ee.taltech.procurementSystemBackend.models.search.DocumentSearch;
import ee.taltech.procurementSystemBackend.service.DocumentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/documents")
public class DocumentController extends ControllerBase<Document, DocumentDto, DocumentSearch>{

//    private final DocumentService documentService;

    public DocumentController(DocumentService service) {
        super(service);
//        documentService = service;
    }

}
