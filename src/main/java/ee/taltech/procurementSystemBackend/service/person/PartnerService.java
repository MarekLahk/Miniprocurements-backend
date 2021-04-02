package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.PartnerMapper;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.repository.person.PartnerRepository;
import org.springframework.stereotype.Service;


@Service
public class PartnerService extends PersonServiceInterface<Partner, PartnerDto> {

    public PartnerService(PartnerRepository partnerRepository) {
        super(partnerRepository, PartnerMapper.INSTANCE);
    }


}
