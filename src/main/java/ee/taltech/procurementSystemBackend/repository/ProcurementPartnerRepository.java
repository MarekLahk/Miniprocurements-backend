package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcurementPartnerRepository extends RepositoryInterface<ProcurementPartner> {

    Optional<ProcurementPartner> findByLinkId(UUID linkId);
}