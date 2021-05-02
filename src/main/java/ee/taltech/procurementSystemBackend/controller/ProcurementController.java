package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.search.ProcurementSearch;
import ee.taltech.procurementSystemBackend.service.ProcurementService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/procurements")
public class ProcurementController extends ControllerBase<Procurement, ProcurementDto, ProcurementSearch>{

    private final ProcurementService procurementService;

    public ProcurementController(ProcurementService procurementService) {
        super(procurementService, Procurement.class, ProcurementDto.class);
        this.procurementService = procurementService;
    }

    @PostMapping
    public ProcurementDto addProcurement(@Valid @RequestBody ProcurementDto dto,
                                         Authentication authentication) {
        return procurementService.addProcurement(dto, authentication);
    }

    @PutMapping("{id}")
    public ProcurementDto updateProcurement(@PathVariable Integer id,
                                            @Valid @RequestBody ProcurementDto dto,
                                            Authentication authentication) {
        return procurementService.updateProcurement(id, dto, authentication);
    }

    @PatchMapping("{id}")
    public ProcurementDto patchProcurementStatus(@PathVariable Integer id,
                                                 @RequestBody ProcurementDto dto,
                                                 Authentication authentication) {
        return procurementService.patchProcurementStatus(id, dto, authentication);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteProcurement(@PathVariable Integer id) {
        procurementService.deleteProcurement(id);
    }
}
