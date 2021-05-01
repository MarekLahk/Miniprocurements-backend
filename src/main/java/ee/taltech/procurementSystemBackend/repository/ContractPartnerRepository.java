package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractPartnerRepository extends RepositoryInterface<ContractPartner> {

    Optional<ContractPartner> findByContractPartnerLinkId(UUID linkId);
}