package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.model.person.Partner;
import ee.taltech.procurementSystemBackend.model.search.person.PartnerSearch;
import ee.taltech.procurementSystemBackend.service.person.PartnerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/partner")
public class PartnerController extends PersonControllerInterface<Partner, PartnerSearch> {

    public PartnerController(PartnerService partnerService) {
        super(partnerService);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
