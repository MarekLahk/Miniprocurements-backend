package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnersDto;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinners;
import ee.taltech.procurementSystemBackend.models.search.ProcurementWinnersSearch;
import ee.taltech.procurementSystemBackend.service.ProcurementWinnersService;
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
public class ProcurementWinnersController extends ControllerBase<ProcurementWinners, ProcurementWinnersDto, ProcurementWinnersSearch> {

    private final ProcurementWinnersService procurementWinnersService;

    public ProcurementWinnersController(ProcurementWinnersService procurementWinnersService) {
        super(procurementWinnersService);
        this.procurementWinnersService = procurementWinnersService;
    }

    @PostMapping
    public ResponseEntity<ProcurementWinnersDto> addProcurementWinner(@Valid @NotNull @RequestBody ProcurementWinnersDto dto,
                                                      Authentication authentication) {
        ProcurementWinnersDto result = procurementWinnersService.addProcurementWinner(dto, authentication);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}

