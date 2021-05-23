package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.FileException;
import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.models.Dto.FileDto;
import ee.taltech.procurementSystemBackend.models.mapper.DocumentMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.model.Document;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileService {

    private final DocumentService documentService;
    private final AuthUtils authUtils;
    private final DocumentMapper documentMapper = DocumentMapper.INSTANCE;
    private final BidRepository bidRepository;

    @NotNull
    @Value( "${spring.files.path}" )
    private String filePath;

    public FileService(DocumentService documentService, AuthUtils authUtils, BidRepository bidRepository) {
        this.documentService = documentService;
        this.authUtils = authUtils;
        this.bidRepository = bidRepository;
    }

    private Document addNewDocument(Document document) {
        return documentService.addDocument(document);
    }



    public DocumentDto storeFile(MultipartFile file, FileDto fileDto, Authentication authentication) {
        if (file.isEmpty()) {
            throw new FileException("File cannot be empty!");
        }
        if (file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new FileException("File name cannot be empty!");
        }
        Document.DocumentBuilder documentBuilder = Document.builder();

        if (fileDto.getAnnouncementId() != null && fileDto.getProcurementId() == null && fileDto.getReplyId() == null) {
            documentBuilder.announcementId(fileDto.getAnnouncementId());
        } else  if (fileDto.getAnnouncementId() == null && fileDto.getProcurementId() != null && fileDto.getReplyId() == null) {
            documentBuilder.procurementId(fileDto.getProcurementId());
        } else if (fileDto.getAnnouncementId() == null && fileDto.getProcurementId() == null && fileDto.getReplyId() != null) {
            documentBuilder.replyId(fileDto.getReplyId());
        } else {
            throw new FileException("File needs to be connected to a parent");
        }
        Person person = authUtils.getPersonToPerformOperations(authentication);
        documentBuilder
                .personId(person.getId())
                .name(file.getOriginalFilename())
                .path(filePath)
        ;
        Document document = documentBuilder.build();
        document = saveFile(file, document);

        return documentMapper.toDto(document);
    }

    public ResponseEntity<Resource> loadAsResource(UUID fileUUID) {
        Document document = documentService.getByUUID(fileUUID).orElseThrow(()-> new FileException("No such file"));
        try {
            Resource file = new ByteArrayResource(Files.readAllBytes(Path.of(filePath + fileUUID)));
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + document.getName() + "\"").body(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException("Error downloading the file");
        }

    }

    public DocumentDto handlePublicFileUpload(UUID bidLinkUUID, MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileException("File cannot be empty!");
        }
        if (file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new FileException("File name cannot be empty!");
        }
        Document.DocumentBuilder documentBuilder = Document.builder();

        Bid bid = bidRepository.findFirstByLinkIdAndStatus(bidLinkUUID, 1)
                .orElseThrow(() -> new FileException("No such bid link ID"));

        documentBuilder
                .personId(bid.getProcurementPartner().getPartnerId())
                .bidId(bid.getId())
                .name(file.getOriginalFilename())
                .path(filePath)
        ;
        Document document = documentBuilder.build();
        document = saveFile(file, document);


        return documentMapper.toDto(document);
    }

    private Document saveFile(MultipartFile file, Document document) {
        document = addNewDocument(document);
        System.out.println(document);

        File file1 = new File(filePath + document.getLinkId());
        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


}
