package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnerDto;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinner;
import ee.taltech.procurementSystemBackend.models.search.ProcurementWinnerSearch;
import ee.taltech.procurementSystemBackend.service.ProcurementWinnerService;
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
@RequestMapping("api/procurement-winners")
public class ProcurementWinnerController extends ControllerBase<ProcurementWinner, ProcurementWinnerDto, ProcurementWinnerSearch> {

    private final ProcurementWinnerService procurementWinnerService;

    public ProcurementWinnerController(ProcurementWinnerService procurementWinnerService) {
        super(procurementWinnerService);
        this.procurementWinnerService = procurementWinnerService;
    }

    @PostMapping
    public ResponseEntity<ProcurementWinnerDto> addProcurementWinner(@Valid @NotNull @RequestBody ProcurementWinnerDto dto,
                                                                     Authentication authentication) {
        ProcurementWinnerDto result = procurementWinnerService.addProcurementWinner(dto, authentication);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}

