package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MiniprocurementPartnerRepository extends RepositoryInterface<MiniprocurementPartner> {

    Optional<MiniprocurementPartner> findByMiniprocurementPartnerLinkId(UUID linkId);
}