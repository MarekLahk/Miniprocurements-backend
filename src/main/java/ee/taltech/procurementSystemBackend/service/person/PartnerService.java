package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.person.Partner;
import ee.taltech.procurementSystemBackend.repository.person.PartnerRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PartnerService extends PersonServiceInterface<Partner> {

    private final PartnerRepository partnerRepository;

    public PartnerService(PersonRepositoryInterface<Partner> personRepository, PartnerRepository partnerRepository) {
        super(personRepository);
        this.partnerRepository = partnerRepository;
    }

    public Optional<Partner> getPartnerByRegNr(Long regNr) {
        return partnerRepository.findPartnerByRegNr(regNr);
    }

}
