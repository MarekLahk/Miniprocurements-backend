package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.models.search.MiniprocurementSearch;
import ee.taltech.procurementSystemBackend.service.MiniprocurementService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/procurements")
public class MiniprocurementController extends ControllerBase<Miniprocurement, MiniProcurementDto, MiniprocurementSearch>{

    private final MiniprocurementService miniprocurementService;

    public MiniprocurementController(MiniprocurementService miniprocurementService) {
        super(miniprocurementService, Miniprocurement.class, MiniProcurementDto.class);
        this.miniprocurementService = miniprocurementService;
    }

    @PostMapping
    public MiniProcurementDto addProcurement(@Valid @RequestBody MiniProcurementDto dto,
                                             Authentication authentication) {
        return miniprocurementService.addProcurement(dto, authentication);
    }

    @PutMapping("{id}")
    public MiniProcurementDto updateProcurement(@PathVariable Integer id,
                                                @Valid @RequestBody MiniProcurementDto dto,
                                                Authentication authentication) {
        return miniprocurementService.updateProcurement(id, dto, authentication);
    }

    @PatchMapping("{id}")
    public MiniProcurementDto patchProcurementStatus(@PathVariable Integer id,
                                                         @RequestBody MiniProcurementDto dto,
                                                     Authentication authentication) {
        return miniprocurementService.patchProcurementStatus(id, dto, authentication);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteProcurement(@PathVariable Integer id) {
        miniprocurementService.deleteProcurement(id);
    }
}
