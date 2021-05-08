package ee.taltech.procurementSystemBackend.controller.Files;

import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/public/files")
@AllArgsConstructor
public class FilesPublicController {

    private final FileService fileService;

    @PostMapping("/{bidLinkUUID}")
    public DocumentDto handleFileUpload(@PathVariable("bidLinkUUID")UUID bidLinkUUID,
                                        @RequestParam("file") MultipartFile file
    ) {


//        return fileService.storeFile(file);
        return null;
    }
}
