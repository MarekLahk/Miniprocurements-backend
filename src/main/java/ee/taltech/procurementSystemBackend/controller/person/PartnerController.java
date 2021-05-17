package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.controller.ControllerBase;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.models.search.person.PartnerSearch;
import ee.taltech.procurementSystemBackend.service.person.PartnerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("api/partners")
public class PartnerController extends ControllerBase<Partner, PartnerDto, PartnerSearch> {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        super(partnerService);
        this.partnerService = partnerService;
    }

    //Note: Contains all search attributes of PersonControllerInterface

    @PostMapping
    public PartnerDto addPartner(@Valid @NotNull @RequestBody PartnerDto dto) {
        return partnerService.addPartner(dto);
    }

    @PutMapping("{id}")
    public PartnerDto updatePartner(@PathVariable Integer id,
                                    @Valid @NotNull @RequestBody PartnerDto dto) {
        return partnerService.updatePartner(id, dto);
    }

}
