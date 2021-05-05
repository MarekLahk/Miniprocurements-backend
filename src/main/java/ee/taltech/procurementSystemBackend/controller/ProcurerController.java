package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurerDto;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import ee.taltech.procurementSystemBackend.models.search.ProcurerSearch;
import ee.taltech.procurementSystemBackend.service.ProcurerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/procurers")
public class ProcurerController extends ControllerBase<Procurer, ProcurerDto, ProcurerSearch> {

    private final ProcurerService procurerService;

    public ProcurerController(ProcurerService procurerService) {
        super(procurerService, Procurer.class, ProcurerDto.class);
        this.procurerService = procurerService;
    }

    @PostMapping
    public ResponseEntity<Void> addProcurer(@Valid @NotNull @RequestBody ProcurerDto dto,
                                      Authentication authentication) {
        procurerService.addProcurer(dto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
