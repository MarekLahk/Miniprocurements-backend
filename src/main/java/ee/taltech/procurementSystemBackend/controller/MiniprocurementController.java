package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.models.search.MiniprocurementSearch;
import ee.taltech.procurementSystemBackend.service.MiniprocurementService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/procurement")
public class MiniprocurementController extends ControllerBase<Miniprocurement, MiniProcurementDto, MiniprocurementSearch>{

    private final MiniprocurementService miniprocurementService;

    public MiniprocurementController(MiniprocurementService miniprocurementService) {
        super(miniprocurementService, Miniprocurement.class, MiniProcurementDto.class);
        this.miniprocurementService = miniprocurementService;
    }

    @PostMapping
    public MiniProcurementDto addProcurement(@Valid @RequestBody MiniProcurementDto dto) {
        return miniprocurementService.addProcurement(dto);
    }

    @PutMapping("{id}")
    public MiniProcurementDto updateProcurement(@Valid @PathVariable Integer id, @RequestBody MiniProcurementDto dto) {
        return miniprocurementService.updateProcurement(id, dto);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteProcurement(@PathVariable Integer id) {
        miniprocurementService.deleteProcurement(id);
    }
}
