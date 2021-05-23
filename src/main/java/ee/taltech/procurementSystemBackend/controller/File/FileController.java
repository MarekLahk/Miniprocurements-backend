package ee.taltech.procurementSystemBackend.controller.File;

import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.models.Dto.FileDto;
import ee.taltech.procurementSystemBackend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/files")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;


    @GetMapping("/{uuid}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable UUID uuid) {



        return fileService.loadAsResource(uuid);

    }

    @PostMapping
    public DocumentDto handleFileUpload(@ModelAttribute FileDto fileDto,
                                        Authentication authentication) {

        System.out.println(fileDto);
        return fileService.storeFile(fileDto.getFile(), fileDto, authentication);
    }
}
