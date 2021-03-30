package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Partner;

import java.util.Optional;

public interface PartnerRepository extends PersonRepositoryInterface<Partner> {

    Optional<Partner> findPartnerByRegNr(Long regNr);

}
