package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.controller.ControllerBase;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.models.search.person.PartnerSearch;
import ee.taltech.procurementSystemBackend.service.person.PartnerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/partner")
public class PartnerController extends ControllerBase<Partner, PartnerDto, PartnerSearch> {

    public PartnerController(PartnerService partnerService) {
        super(partnerService, Partner.class, PartnerDto.class);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
