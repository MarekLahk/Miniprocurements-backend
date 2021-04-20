package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.models.Dto.MiniprocurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.search.MiniprocurementPartnerSearch;
import ee.taltech.procurementSystemBackend.service.MiniprocurementPartnerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/miniprocurementPartners")
public class MiniprocurementPartnerController extends ControllerBase<MiniprocurementPartner, MiniprocurementPartnerDto, MiniprocurementPartnerSearch> {

    private final MiniprocurementPartnerService miniprocurementPartnerService;

    public MiniprocurementPartnerController(MiniprocurementPartnerService miniprocurementPartnerService) {
        super(miniprocurementPartnerService, MiniprocurementPartner.class, MiniprocurementPartnerDto.class);
        this.miniprocurementPartnerService = miniprocurementPartnerService;
    }

    @PostMapping
    public MiniprocurementPartnerDto addMiniprocurementPartner(@Valid @RequestBody MiniprocurementPartnerDto dto) {
        return miniprocurementPartnerService.addMiniprocurementPartner(dto);
    }

    @PutMapping("{id}")
    public MiniprocurementPartnerDto updateMiniprocurementPartner(@PathVariable Integer id, @RequestBody MiniprocurementPartnerDto dto) {
        return miniprocurementPartnerService.updateMiniprocurementPartner(id, dto);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteMiniprocurementPartner(@PathVariable Integer id) {
        miniprocurementPartnerService.deleteMiniprocurementPartner(id);
    }

}